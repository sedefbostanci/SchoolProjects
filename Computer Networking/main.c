 #include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct clientsStr{
    char ID[20];
    char IP[20];
    char macAdress[10];
    char *route[80];
};

struct Frame{
    int top;
    int capacity;
    char* layer[80];
};

struct Queue {
    char* frame[30][80];
    int front,rear,size;
    unsigned capacity;

};
struct log{
    int receivecont;
    int sendcont;
    int hop;
    char *ID;
};

int commandexecute(FILE* commands,int max_msg_size,struct clientsStr client[],int numofclients,char* out_port,char* in_port);
struct Queue * message(char line[],int max_msg_size,struct clientsStr client[],int numofclients,char* out_port,char* in_port,int control);
void enqueue(struct Queue** queue, struct Frame*frames);
struct Frame *FindRoute(char* senderID,char* lastreceiverID,struct clientsStr client[],int numofclients,char* message,char* out_port,char* in_port,int numofframes,int counter);
void push(struct Frame *frames,int which,char* item);
void insert(struct Queue* queue, char*** arr,int which);
char find(int numofclients,struct clientsStr client[],char* senderID,char* lastreceiverID);
int send(struct Queue* outQueue,char* senderID,char* lastID,char* receiverID,int numofclients,struct clientsStr client[],char* first,int counter,char*** arr,struct Queue* in,struct log* logarr,int logcounter);
void Remove(struct Queue* queue);
void pop(struct Frame* frames);

int main(int argc, char *argv[])
{
    int max_msg_size;
    char*out_port;
    char*in_port;
    char line[100];
    int numofclients,ch;
    int counter=0,m=0,i=0,sayac2=0;
    char line1[2];


    FILE *clients,*routing,*commands;

    /* OPen files*/
    clients= fopen(argv[1], "r");
    routing=fopen(argv[2], "r");
    commands=fopen(argv[3], "r");

    /*Take arguments */
    max_msg_size=atoi(argv[4]);
    out_port=argv[5];
    in_port=argv[6];

    while(!feof(clients))
    {
        ch = fgetc(clients);
        if(ch == '\n')
        {
            sayac2++;
        }
    }
    fseek(clients,0,SEEK_SET);
    fgets(line1,2,clients); /*Take first line from clients file and assign it number of clients */

    numofclients=atoi(line1);

    struct clientsStr *client; /* Dynamic client struct to hold informations about clients*/
    client = (struct clientsStr*)malloc(numofclients*sizeof(struct clientsStr));

    /*Read client file line by line and load it to client struct array*/
    while((fscanf(clients,"%s %s %s\n",client[counter].ID,client[counter].IP,client[counter].macAdress)) != EOF ){
        counter++;
    }

    while(fscanf(routing,"%s",line) != EOF ){ /* Read routing file line by line and assign it to route array which is in client struct array*/

        if(strcmp(line,"-")==0 && m==(numofclients-1)*2){
            i++;
            m=0;
        }
        else{
            memcpy(&client[i].route[m],&line,sizeof(line));
            m++;
        }
    }

    commandexecute(commands,max_msg_size,client,numofclients,out_port,in_port); /*Call function to execute command file*/

    free(client);
    fclose(clients);
    fclose(routing);

    return 0;

}

int commandexecute(FILE *commands,int max_msg_size,struct clientsStr client[],int numofclients,char* out_port,char* in_port){

    char line[100],line1[100];
    char* token;
    int numoflines,i,j,num;
    int a=0,control=0,control2=0,logcounter=1;
    char* arr[100];
    char* lastID;
    char* senderID;
    char* nextMac;
    char* nextID;
    lastID = (char*)malloc(10*sizeof(char));
    senderID = (char*)malloc(10*sizeof(char));
    char* msg;


    fgets(line1,sizeof(line1),commands);

    char *** arr1 = malloc(100 * sizeof(char **)); /* It is for hold frames */
    for(i = 0; i < 100; ++i)
    {
        arr1[i] = malloc(12 * sizeof(char *));
    }
    for(i = 0; i < 100; ++i){
        for(j = 0; j < 12; ++j){
            arr1[i][j] = malloc(max_msg_size * sizeof(char));
        }
    }

    struct log* logarr=(struct log*) malloc(numofclients*sizeof(struct log)); /* Struct array to hold log informations */

    numoflines=atoi(line1);

    printf("---------------------------------------------------------------------------------------\n");

    struct Queue* Outqueue = (struct Queue*) malloc(100*sizeof(struct Queue)); /*Dynamic Outgoing queue and incoming queue as struct array */
    struct Queue* in = (struct Queue*) malloc(100*sizeof(struct Queue));


    while (fgets(line, sizeof(line), commands) != NULL){ /*Take lines from commands file split them from space character and store array*/

        arr[a]=malloc(sizeof(line));
        strcpy(arr[a], line);

        token = strtok(line, " ");

        while( token != NULL ) {

            if(strcmp(token,"MESSAGE")==0){ /* If first command is MESSAGE  */
                lastID[0]=arr[a][10]; /* Take sender ID and receiver ID */
                lastID[1]='0';
                senderID[0]=arr[a][8];
                senderID[1]='0';

                printf("Command: %s",arr[a]); /* print line */
                printf("---------------------------------------------------------------------------------------");
                msg = (char*)malloc(strlen(arr[a])*sizeof(char)); /* It is for send printLog function */
                msg=arr[a];

                Outqueue=message(arr[a],max_msg_size,client,numofclients,out_port,in_port,control); /*Call message function which is return outgoing queue*/
                Outqueue = (struct Queue *) realloc(Outqueue,  (Outqueue->size) * sizeof(struct Queue)); /*Realloc and outgoing queue depending on size of returning queue from message function*/
                in = (struct Queue *) realloc(in,  (Outqueue->size) * sizeof(struct Queue));/*Realloc incoming queue too */

                in->size=in->front=0; /* Incoming queue size front and rear information */
                in->capacity=Outqueue->capacity;
                in->rear=Outqueue->capacity-1;


                arr1=realloc(arr1,(Outqueue->size)*sizeof(char**));
                nextMac=Outqueue[0].frame[3][1]; /* Took that to send it to send function as parameter */
                num=Outqueue->size;

                logarr[0].ID=senderID[0]; /*Load log informations of first sender */
                logarr[0].receivecont=1;
                logarr[0].hop=0;
                logarr[0].sendcont=1;


                for(i=0;i<numofclients;i++){ /* It is for send to send function too */
                    if(strcmp(client[i].macAdress,nextMac)==0){
                        nextID=client[i].ID;
                    }
                }

            }
            else if(strcmp(token,"SEND")==0){ /* Command is SEND*/
                control2++;
                printf("----------------\n");
                printf("Command: %s",arr[a]);
                printf("----------------\n");
                send(Outqueue,senderID,lastID,nextID,numofclients,client,senderID,1,arr1,in,logarr,logcounter); /*Call send function */


            }
            else if(strcmp(token,"SHOW_Q_INFO")==0){ /* Command is SHOW_Q_INFO*/
                printf("-------------------------\n");
                printf("Command: %s",arr[a]);
                printf("-------------------------\n");

                if(senderID[0]==arr[a][12]){ /* If ID of show queue info is equal first sender ID */

                    if(control2==0){ /* Control if it is before SEND command or not if it is before SEND command control2 variable equal 0*/

                        /* If control2 is zero check which queue information is requested */
                        if(arr[a][14]=='o'){ /*If it is  outgoing queue */
                            printf("Client %c Outgoing Queue Status\n",senderID[0]);
                            printf("Current total number of frames: %d\n",Outqueue->size);
                        }
                        else{ /* If it is incoming queue, number of frames is 0 because it is first sender and there is nothing on its incoming queue*/
                            printf("Client %c Incoming Queue Status\n",senderID[0]);
                            printf("Current total number of frames: 0\n");
                        }
                    }
                    else{
                        if(arr[a][14]=='o'){/* If that command come after SEND command first senders outgoing queue and incoming queue is empty so number of frames is zero*/

                            printf("Client %c Outgoing Queue Status\n",senderID[0]);
                            printf("Current total number of frames: 0\n");
                        }
                        else{
                            printf("Client %c Incoming Queue Status\n",senderID[0]);
                            printf("Current total number of frames: 0\n");
                        }
                    }
                }
                else if(Outqueue[0].frame[0][1][0]==arr[a][12]){ /* If requested Id is last receiver ID */
                    if(control2==0){
                             if(arr[a][14]=='o'){/* If that command come before SEND its outgoing queue is empty*/
                                printf("Client %c Outgoing Queue Status\n",Outqueue[0].frame[0][1][0]);
                                printf("Current total number of frames: 0\n");
                             }
                             else{
                                printf("Client %c Incoming Queue Status\n",Outqueue[0].frame[0][1][0]);
                                printf("Current total number of frames: 0\n");
                             }
                    }
                    else{  /* If that command come after SEND */
                        if(arr[a][14]=='o'){ /* If requested queue is outgoing  it is empty*/
                            printf("Client %c Outgoing Queue Status\n",Outqueue[0].frame[0][1][0]);
                            printf("Current total number of frames: 0\n");

                        }
                        else{ /* If requested queue is incoming*/
                            printf("Client %c Incoming Queue Status\n",Outqueue[0].frame[0][1][0]);
                            printf("Current total number of frames: %d\n",num);
                        }
                    }
                }
                else{ /*If requested Id is neither first sender nor receiver it is empty*/
                    if(arr[a][14]=='o'){
                        printf("Client %c Outgoing Queue Status\n",arr[a][12]);
                        printf("Current total number of frames: 0\n");
                    }
                    else{
                        printf("Client %c Incoming Queue Status\n",arr[a][12]);
                        printf("Current total number of frames: 0\n");
                    }
                }
            }
            else if(strcmp(token,"SHOW_FRAME_INFO")==0){ /* If command is show frame info */
                printf("--------------------------------\n");
                printf("Command: %s",arr[a]);
                printf("--------------------------------\n");

                if(senderID[0]==arr[a][16]){ /* If requested Id is first sender */

                    if(control2==0){ /* check if it is before SEND command or not */

                        if(arr[a][18]=='o'){ /* If it is outgoing queue of first sender is not empty so send that queue to showFrame function*/
                            showFrame(Outqueue,arr[a][22],arr[a][16],arr[a][18],senderID,nextID);
                        }
                        else{/* If it is incoming queue of first sender queue is empty*/
                            printf("No such frame.\n");
                        }
                    }
                    else{/* check if it is after SEND command first senders incoming and outgoing queue is empty */
                        printf("No such frame.\n");
                    }
                }
                else if(lastID[0]==arr[a][16]){ /* If requested Id is receiver */

                    if(control2==0){ /* if it is before SEND command queue is empty */
                        printf("No such frame.\n");
                    }
                    else{/* if it is after SEND command queue is empty */
                        if(arr[a][18]=='o'){ /* outgoing queue of receiver is empty*/
                            printf("No such frame.\n");

                        }
                        else{ /* Its incoming queue is not empty send it to showFrame function*/
                            showFrame(in,arr[a][21],arr[a][16],arr[a][18],senderID,nextID);
                        }
                    }
                }
                else{/*If requested Id is neither first sender nor receiver it is empty*/

                    printf("No such frame.\n");
                }
            }
            else if(strcmp(token,"PRINT_LOG")==0){
                printf("--------------------\n");
                printf("Command: %s",arr[a]);
                printf("--------------------\n");
                if(in->size==0){
                  printLog(logarr,arr[a][10],senderID[0],lastID[0],msg,Outqueue->size,numofclients);  /* Send log struct array to printLog function*/
                }
                else{
                   printLog(logarr,arr[a][10],senderID[0],lastID[0],msg,in->size,numofclients);
                }

            }

            token = strtok(NULL," ");
        }

    }

    fclose(commands);
    free(lastID);
    free(senderID);
    free(arr1);
    free(Outqueue);
    free(msg);
    free(logarr);

    return 0;
}
struct Queue *message(char line[],int max_msg_size,struct clientsStr client[],int numofclients,char* out_port,char* in_port,int control){

    int i,numofframes,a,v,y,j;
    char sender_ID;
    char receiver_ID;
    int lenofmessage=0,b=0,counter=1,count=0,counter2=0,sayac=0,sayac2=1;
    char* message;
    char* sender;
    char* receiver;


    message=(char*)malloc(max_msg_size*sizeof(char));

    printf("\nMessage to be sent: ");


    for(i=13;i<strlen(line)-2;i++){ /* Print message*/
        printf("%c",line[i]);
        lenofmessage++;
    }
    printf("\n");
    printf("\n");

	sender=(char*)malloc(3*sizeof(char));
	receiver=(char*)malloc(3*sizeof(char));

    sender_ID=line[8];
    receiver_ID=line[10];
    (*sender)=&(line[8]);
    (*receiver)=&(line[10]);

    if(lenofmessage%max_msg_size != 0){ /* Find number of frames*/
        numofframes=lenofmessage/max_msg_size+1;
    }
    else{
        numofframes=lenofmessage/max_msg_size;
    }

    struct Frame *frame; /*It is for stack*/

    struct Queue* Outqueue = (struct Queue*) malloc(numofframes*sizeof(struct Queue));/*Outgoing queue as struct array*/

    Outqueue->capacity=numofframes;
    Outqueue->front = Outqueue->size = 0;
    Outqueue->rear =numofframes - 1;

    char *** arr = malloc(numofframes * sizeof(char **)); /*It is for copy stack information*/
    for(i = 0; i < numofframes; ++i){
        arr[i] = malloc(12 * sizeof(char *));
    }
    for(i = 0; i < numofframes; ++i){
        for(j = 0; j < 12; ++j){
            arr[i][j] = malloc(max_msg_size * sizeof(char));
        }
    }


    for(a=13;a<strlen(line)-2;a++){ /*Split message according to maximum message size and store it to array*/

        if(counter != numofframes){
            if(b != max_msg_size){
                message[b]=line[a];
                b++;
            }
            if(b==max_msg_size){

                message[b]='\0';

                /*Call function and send generated message*/
                frame=FindRoute(sender_ID,receiver_ID,client,numofclients,message,out_port,in_port,numofframes,sayac2);/* This function returns generated frames one by one*/
                sayac2++;

                for(v=0;v<4;v++){ /* Copy stack to insert it to queue*/
                    for(y=0;y<3;y++){

                        if(frame[v].layer[y] != NULL){
                            strcpy(arr[count][counter2],frame[v].layer[y]);
                            counter2++;
                        }
                        else{
                            counter2++;
                        }
                    }
                }

                arr[count][0]=sender;
                arr[count][1]=receiver;

                counter2=0;
                count++;

                counter++;
                message[0]='\0'; /* Finish first part of splitted message and go on*/
                b=0;
            }

        }
        else{ /* This part for if remaining part of message smaller than max message size*/
            message[b]=line[a];
            if(a==strlen(line)-3){
                message[b+1]='\0';

                frame=FindRoute(sender_ID,receiver_ID,client,numofclients,message,out_port,in_port,numofframes,sayac2);
                sayac2++;

                for(v=0;v<4;v++){
                    for(y=0;y<3;y++){

                       if(frame[v].layer[y] != NULL){
                            strcpy(arr[count][counter2],frame[v].layer[y]);
                            counter2++;
                        }
                        else{
                            counter2++;
                        }
                    }
                }

                counter2=0;
                count++;

            }
            else{
                b++;
            }
        }
    }

     while(sayac!=numofframes){ /* Insert generated frame to outgoing queue*/
        insert(Outqueue,arr,sayac);
        sayac++;
    }

    free(message);
    free(arr);
    free(sender);
    free(receiver);


    return Outqueue;

}
struct Frame *FindRoute(char* senderID,char* lastreceiverID, struct clientsStr client[],int numofclients,char* message,char* out_port,char* in_port,int numofframes,int counter){

    int i,sender_ID,ID,receiver_ID,routeID,j,h;
    char next;
    char* senderIP;
    char* lastIP;
    char* sendermac;
    int next_ID,v,ID2;
    char* nextmac;
    char x[5],y[5];

    sender_ID=senderID -'0';
    receiver_ID=lastreceiverID -'0';

    for(i=0;i<numofclients;i++){ /*Look for sender index in client struct array*/
        ID=client[i].ID[0]-'0';
        if(sender_ID==ID){
           senderIP=client[i].IP; /*Took its other information to store stack*/
           sendermac=client[i].macAdress;
           for(j=0;j<(numofclients-1)*2;j=j+2){
                x[0]=client[i].route[j];
                x[1]='0';
                routeID=x[0];

                if(routeID==receiver_ID+48){ /* Find next receiver ID from routing table*/

                    y[0]=(int)client[i].route[j+1];
                    y[1]='0';
                    int asci=y[0];
                    next=asci;
                }

           }
        }
    }

    for(h=0;h<numofclients;h++){
        ID=client[h].ID[0]-'0';
        if(receiver_ID==ID){
           lastIP=client[h].IP;
        }
    }

    struct Frame* frames; /* Create stack*/
    frames = (struct Frame*)malloc(4*sizeof(struct Frame));

    frames->top=-1;
    frames->capacity=4;

    push(frames,0,&senderID); /* Fill frames layer by layer*/
    push(frames,1,&lastreceiverID);
    push(frames,2,message);
    push(frames,0,out_port);
    push(frames,1,in_port);
    push(frames,0,senderIP);
    push(frames,1,lastIP);


    int ascii=next;

    for(v=0;v<numofclients;v++){ /* Find next receiver mac adress*/
        ID2=client[v].ID[0]-'0'+48;
        if(ascii==ID2){
           nextmac=client[v].macAdress;

        }
    }

    push(frames,0,sendermac); /*Push it to last layer*/
    push(frames,1,nextmac);

    printf("Frame #%d\n",counter); /*Pop generated frame and print it layer by layer*/

    printf("Sender MAC address: %s, Receiver MAC adress: %s\n",frames[frames->top].layer[0],frames[frames->top].layer[1]);
     pop(frames);
     printf("Sender IP address: %s, Receiver IP address: %s\n",frames[frames->top].layer[0],frames[frames->top].layer[1]);
     pop(frames);
     printf("Sender port number %s, Receiver port number: %s\n",frames[frames->top].layer[0],frames[frames->top].layer[1]);
     pop(frames);
     printf("Sender ID: %s, Receiver ID: %s\n",frames[frames->top].layer[0],frames[frames->top].layer[1]);
    printf("Message chunk carried: %s\n",frames[frames->top].layer[2]);
    printf("--------\n");

    free(frames);

    return frames;
}

void push(struct Frame *frames,int which,char* item){

    if(which==0){ /*Check if it is first layer or not. If "which" variable is 0 it means next layer informations is coming so increase frames top by 1*/
      frames[++frames->top].layer[which]=item;
    }
    else{
      frames[frames->top].layer[which]=item;
    }
    return;
}

void pop(struct Frame* frames){ /* Pop stack by increase its top */

    frames->top--;
}

void insert(struct Queue* queue, char*** arr,int which){ /*Store queue frame by frame. "which" value is incremented when calling insert function */

    int i,j;
    int p=0;
    queue->rear = (queue->rear + 1)%queue->capacity;

    for(i=0;i<4;i++){
        for(j=0;j<3;j++){

            queue[queue->rear].frame[i][j]=arr[which][p]; /*If which is zero it means first frame ,if it is one it means second frame and so on*/
            p++;
        }
    }
    queue->size = queue->size + 1;

    return;
}

void Remove(struct Queue* queue) /* Increase front value of queue and decrease size when removing frames from queue*/
{
    queue->front = (queue->front + 1)%queue->capacity;
    queue->size = queue->size - 1;

}

int send(struct Queue* outQueue,char* senderID,char* lastID,char* receiverID,int numofclients,struct clientsStr client[],char* first,int counter,char*** arr,struct Queue* in,struct log* logarr,int logcounter){

    char next;
    int i,ID,receiver_ID,a;
    char *nextMac;
    char *receiverMac;
    char *nextID;

    receiver_ID =receiverID[0]-'0'+48;

    struct Queue* Inqueue = (struct Queue*) malloc((outQueue->size)*sizeof(struct Queue)); /* Create incoming and new outgoing queue*/
    Inqueue->size=Inqueue->front=0;
    Inqueue->capacity=outQueue->capacity;
    Inqueue->rear=outQueue->capacity-1;

    struct Queue* newOutqueue = (struct Queue*) malloc((outQueue->size)*sizeof(struct Queue));
    newOutqueue->size=newOutqueue->front=0;
    newOutqueue->capacity=outQueue->capacity;
    newOutqueue->rear=outQueue->capacity-1;

    while(outQueue->size > 0){ /*Copy outgoing queue to incoming by calling remove function*/
       copy(outQueue,arr);
       insert(Inqueue,arr,outQueue->front);
       Remove(outQueue);
       logarr[logcounter].hop=counter; /*Store log information of second sender when its incoming queue filling*/
        logarr[logcounter].sendcont=1;
        logarr[logcounter].ID=receiverID[0];
        logarr[logcounter].receivecont=1;
    }

    if(Inqueue[0].frame[0][1] != receiverID){ /*Check new sender Id if it is equal destination ID or not */

        printf("A message received by client %c, but intended for client %c. Forwarding...\n",receiver_ID,lastID[0]);

        next=find(numofclients,client,receiverID,lastID); /* If it is not, find next Id*/
        int ascii=next;

        if(next == 45){ /* If returning value of find function is equal "- " */
            logarr[logcounter].sendcont=0;
            logarr[logcounter].hop=counter;
            printf("Error: Unreachable destination. Packets are dropped after %d hops!\n",counter);
        }
        else if(ascii==lastID[0]-'0'+48){ /* If it is requested destination ID*/
            counter++;
            for(i=0;i<numofclients;i++){ /* Take other information of destination ID to change*/
                ID=client[i].ID[0]-'0'+48;
                if(ascii==ID){
                    nextMac=client[i].macAdress;
                    nextID=client[i].ID;
                }
                else if(ID==receiver_ID){
                    receiverMac=client[i].macAdress;
                }
            }
            for(a=0;a<Inqueue->size;a++){ /* Change mac addresses*/
                Inqueue[a].frame[3][0]=receiverMac;
                Inqueue[a].frame[3][1]=nextMac;
                printf("    Frame #%d MAC address change: New sender MAC %s, new receiver MAC %s\n",a+1,Inqueue[a].frame[3][0],Inqueue[a].frame[3][1]);

            }
            while(Inqueue->size > 0){ /* After changing mac address enqueue incoming queue to new outgoing queue*/
               copy(Inqueue,arr);
               insert(newOutqueue,arr,Inqueue->front);
               Remove(Inqueue);
            }

            while(newOutqueue->size > 0){ /* This part is for SHOW_FRAME_INFO command*/
               copy(newOutqueue,arr);
               insert(in,arr,newOutqueue->front);
               Remove(newOutqueue);

            }

            logarr[++logcounter].ID=lastID[0]; /*Store new receiver log information*/
            logarr[logcounter].receivecont=1;
            logarr[logcounter].hop=counter;

            printf("A message received by client %c from client %c after a total of %d hops.\n",lastID[0],first[0],counter);

            return 1;
        }
        else{ /* If returning value of "find" function neither destination ID nor "-" */

            counter++;
            for(i=0;i<numofclients;i++){ /* Take new receiver information */
                ID=client[i].ID[0]-'0'+48;
                if(ascii==ID){
                    nextMac=client[i].macAdress;
                    nextID=client[i].ID;
                }
                else if(ID==receiver_ID){
                    receiverMac=client[i].macAdress;
                }
            }
            for(a=0;a<Inqueue->size;a++){ /* change mac address on each frame */
                Inqueue[a].frame[3][0]=receiverMac;
                Inqueue[a].frame[3][1]=nextMac;
                printf("    Frame #%d MAC address change: New sender MAC %s, new receiver MAC %s\n",a+1,Inqueue[a].frame[3][0],Inqueue[a].frame[3][1]);

            }
            while(Inqueue->size > 0){ /* Enqueue changed frames to next receiver outgoing queue*/
               copy(Inqueue,arr);
               insert(newOutqueue,arr,Inqueue->front);
               Remove(Inqueue);

            }
            logarr[++logcounter].ID=nextID; /* Took its log information */
            logarr[logcounter].receivecont=1;
            logarr[logcounter].hop=counter;
            return send(newOutqueue,receiverID,lastID,nextID,numofclients,client,first,counter,arr,in,logarr,logcounter);/* Call function again*/
        }
    }

    free(Inqueue);
    free(newOutqueue);

    return 0;
}
void copy(struct Queue* outQueue,char*** arr){ /*This function for generate changed frames before insert function */

    int i,j;
    int count=0;

    for(i=0;i<4;i++){
        for(j=0;j<3;j++){
            arr[outQueue->front][count]=outQueue[outQueue->front].frame[i][j];
            count++;
        }
    }

}
char find(int numofclients,struct clientsStr client[],char* senderID,char* lastreceiverID){

    int sender_ID,receiver_ID,ID,i,j,routeID;
    char x[5],y[5];
    char next;

    sender_ID=senderID[0]-'0'+48;
    receiver_ID=lastreceiverID[0] -'0'+48;

    for(i=0;i<numofclients;i++){ /* Look for senderID on client struct array */
        ID=client[i].ID[0]-'0'+48;

        if(sender_ID==ID){

           for(j=0;j<(numofclients-1)*2;j=j+2){ /* Look its routing table*/
                x[0]=client[i].route[j];
                x[1]='0';

                routeID=x[0];

                if(routeID==receiver_ID){ /*Find destination ID on first row of routing table */

                    y[0]=(int)client[i].route[j+1]; /* took next because its neighbor and what we are looking for */
                    y[1]='0';
                    int asci=y[0];
                    next=asci;
                }
           }
        }
    }

    return next;
}
void showFrame(struct Queue* q,char num,char a,char b,char* senderID,char* next){ /* Print frame info of requested ID and requested queue of that client*/

    int n=num-'0';
    if(n <= q->size-1){
        printf("Current Frame #%d on the outgoing queue of client %c\n",n,a);
        printf("Carried Message:  %s \n",q[n-1].frame[0][2]);
        printf("Layer 0 info: Sender ID: %c, Receiver ID: %c\n",senderID[0],next[0]);
        printf("Layer 1 info: Sender port number: %s, Receiver port number: %s\n",q[n-1].frame[1][0],q[n-1].frame[1][1]);
        printf("Layer 2 info: Sender IP address: %s, Receiver IP address: %s\n",q[n-1].frame[2][0],q[n-1].frame[2][1]);
        printf("Layer 3 info: Sender MAC address: %s, Receiver MAC address: %s\n",q[n-1].frame[3][0],q[n-1].frame[3][1]);
    }
    else{
        printf("No such frame.\n");
    }

}

void printLog(struct log *logarr,char ID,char sender,char last,char message[],int numofframes,int numofclient){

    int i,j,a;

    time_t rawtime;
    struct tm *timeinfo;
    time(&rawtime);
    timeinfo=localtime(&rawtime);
    char buffer[26];
    strftime(buffer,26,"%Y-%m-%d %H:%M:%S",timeinfo);

    printf("Client %c Logs: \n",ID);
    printf("-------------\n");
    printf("Log Entry #1\n");
    printf("Timestamp : %s\n",buffer);

    for(i=0;i<numofclient;i++){

        if(logarr[i].ID==ID){ /* Find index of requested ID in logarr array*/
            printf("Message:");
            for(a=13;a<strlen(message)-2;a++){
                printf("%c",message[a]);
            }

            printf("\n");
            printf("Number of frames: %d\n",numofframes);
            printf("Number of hops: %d\n",logarr[i].hop);
            printf("Sender ID: %c\n",sender);
            printf("Receiver ID %c\n",last);
            j=i;
            if(logarr[i].receivecont==1){  /* If frames were dropped */
                printf("Activity : Message Received\n");
                printf("Success: Yes\n");
            }
            else{
                printf("Activity : Message Received\n");
                printf("Success: No\n");
            }
        }
    }

    printf("-------------\n");
    printf("Log Entry #2\n");
    printf("Timestamp : %s\n",buffer);

    printf("Message: ");
    for(a=13;a<strlen(message)-2;a++){
        printf("%c",message[a]);
    }

    printf("\n");
    printf("Number of frames: %d\n",numofframes);
    printf("Number of hops: %d\n",logarr[j].hop);
    printf("Sender ID: %c\n",sender);
    printf("Receiver ID %c\n",last);

    if(logarr[j].sendcont==1){ /* If frames were dropped */
        printf("Activity : Message Forwarded\n");
        printf("Success: Yes\n");
    }
    else{
        printf("Activity : Message Forwarded\n");
        printf("Success: No\n");
    }
}
