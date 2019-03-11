#include <stdio.h>
#include <stdlib.h>
#include <string.h>


struct kayit{
    char type[15];
    char name[15];
    int hp;
    int damage;
    int satir;
    int sutun;
    int xp;
};

struct put1{
    char* Name;
    char* Row;
    char* Column;

};

struct put2{
    char* Name2;
    char* Row2;
    char* Column2;

};

int main(int argc, char *argv[])
{
    /*VARIABLES*/
    int counter=0,counter2=0,counter3=0,sayac=0,sayac2=0,sayac3;
    char* token;
    char* token2;
    char* token3;
    char* komutlar[100];
    char lines[100];
    int i,k,l,n,s,z,h=0,v,a,b,c,g,o,u,w,x,y,yo,ho,po;
    int j=0, d=0,e=0;
    int f=0;
    int m=0,t=0;
    char line[256],line2[250],line3[250];
    char arr[10];
    char* arr2[60];
    char* arr3[60];
    char* arr4[60];
    int N=40;
    int ROW,COLUMN,ROW2,COLUMN2;
    int row,row2,row4;
    int column,column2,column4;
    int numberoflines=0;
    int satiri,sutunu;
    int ch;
    int sedef=0,sedef2=0;
    int monstersayac=0,herosayac=0;


    /* Open files */
    FILE *chars,*command,*output;

    chars = fopen(argv[1], "r");
    command=fopen(argv[2], "r");
    output=fopen(argv[3],"w+");

    /*Checking if files exist*/
    if (chars == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }

    if (command == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }


    /*Read first file line by line to calculate how many line in it*/
    while(!feof(chars))
    {
        ch = fgetc(chars);
        if(ch == '\n')
        {
            sayac2++;
        }
    }

    /*Close and open file to read again*/
    fclose(chars);
    chars = fopen(argv[1], "r");

    sayac3=sayac2+1;

    /*Define array of struct*/
    struct kayit *data;
    data = (struct kayit*)malloc(sayac3*sizeof(struct kayit));


    /*Take data from chars file and store them to array of struct*/
    while((fscanf(chars, "%[^,],%[^,],%d,%d\n", data[sayac].type, data[sayac].name, &data[sayac].hp, &data[sayac].damage))!=EOF){
        sayac++;
    }

    /*Make xp of all heroes and monsters 0*/
    for(y=0;y<sayac;y++){
        data[y].xp=0;
    }


    /*Read first command from command file and take row and column size*/
    fscanf(command,"%s %d %d\n",arr,&i,&k);
    ROW=i;
    COLUMN=k;

    /*Define game map*/
    char **map = malloc((ROW) * sizeof(char *));
    for(i = 0; i < ROW; i++)
        map[i] =malloc((COLUMN) * sizeof(char));

    /*Make point all element of map array*/
    for(s=0;s<ROW;s++){
        for(z=0;z<COLUMN;z++){
            map[s][z]=46;
        }
    }

    /*Read first put command from command file*/
    fgets(line2, sizeof(line2), command);

    /*Split line into token by space and store to an  char array*/
    token = strtok(line2, " ");

    while (token != NULL) {
        arr2[j]=malloc(strlen(token)+1);
        strcpy(arr2[j],token);
        token = strtok(NULL," ");
        j++;
        counter++;
    }

    /*Define array of struct*/
    struct put1 *veri;
    veri = (struct put1*)malloc(((counter-2)/3)*sizeof(struct put1));

    /*Store name, row and column of type to veri array */
    for (i = 2; i < counter; i = i + 3) {
        k = i + 1;
        j = i + 2;
        veri[d].Name = arr2[i];
        veri[d].Row = arr2[k];
        veri[d].Column = arr2[j];
        d++;
    }

    /*Take name from veri array and change point and first letter of that name in map according to that character's row and column*/
    for (i = 0; i < ((counter-2)/3); i++) {
        row = atoi(veri[i].Row);
        column = atoi(veri[i].Column);
        map[row][column] = veri[i].Name[0];
    }

    /*Make same things for second put command*/
    fgets(line3, sizeof(line3), command);

    token2 = strtok(line3, " ");

    while (token2 != NULL) {
        arr3[m]=malloc(strlen(token2)+1);
        strcpy(arr3[m],token2);
        token2 = strtok(NULL," ");
        m++;
        counter2++;
    }


    struct put2 *veri2;
    veri2 = (struct put2*)malloc(((counter2-2)/3)*sizeof(struct put2));

    for (l = 2; l < counter2; l = l + 3) {
        k = l + 1;
        f = l + 2;
        veri2[e].Name2 = arr3[l];
        veri2[e].Row2 = arr3[k];
        veri2[e].Column2 = arr3[f];
        e++;
    }

    for (n = 0; n < ((counter2-2)/3); n++) {
        row2 = atoi(veri2[n].Row2);
        column2 = atoi(veri2[n].Column2);
        map[row2][column2] = veri2[n].Name2[0];
    }

    /*Read command file and store every line to an array*/
    while (fgets (lines, sizeof(lines), command)) {
        komutlar[h]=malloc(sizeof(lines));
        strcpy(komutlar[h], lines);
        h++;
        numberoflines++;
    }

    for(a=0;a<sayac;a++){
        for(c=0;c<(counter-2)/3;c++){
            if(strcmp(data[a].name,veri[c].Name)==0){
                data[a].satir=atoi(veri[c].Row);
                data[a].sutun=atoi(veri[c].Column);
            }
        }
    }
    for(b=0;b<sayac;b++){
        for(c=0;c<(counter2-2)/3;c++){
            if(strcmp(data[b].name,veri2[c].Name2)==0){
                data[b].satir=atoi(veri2[c].Row2);
                data[b].sutun=atoi(veri2[c].Column2);
            }
        }
    }

    /*Calculate how many hero and monster*/
    for(ho=0;ho<sayac;ho++){
        if(data[ho].type[0]==72){
            herosayac++;
        }
        else{
            monstersayac++;
        }
    }

    for (v = 0; v < numberoflines; v++) {/*Take lines first characters from array and make operation according to tahat characters ASCII codes*/
        if (komutlar[v][0] == 83) { /*If it is upper "S" execute show command*/
            if (komutlar[v][5]==72) {/*Take fifth character from same line and if it is "H" show status of heroes*/
                fprintf(output,"HERO STATUS\n");
                for (a = 0; a < sayac; a++) {

                    if (data[a].type[0] == 72) {
                        if(data[a].hp>0){
                            fprintf(output,"%s HP: %d XP: %d\n", data[a].name, data[a].hp,data[a].xp);
                        }
                        else{

                            fprintf(output,"%s HP: 0 XP: %d\n", data[a].name,data[a].xp);
                        }

                    }
                }
                fprintf(output,"\n");
            }
            else if (komutlar[v][6] == 79) {/*If sixth character is "O" show status of monsters*/
                fprintf(output,"MONSTER STATUS\n");
                for (b = 0; b < sayac; b++) {

                    if (data[b].type[0] == 77) {
                        if(data[b].hp>0){
                            fprintf(output,"%s HP: %d\n", data[b].name, data[b].hp);
                        }
                        else{
                            fprintf(output,"%s HP: 0\n", data[b].name);
                        }

                    }

                }
                fprintf(output,"\n");
            }
            else {/*Otherwise show map status*/
                fprintf(output,"MAP STATUS\n");
                for (i = 0; i < ROW; i++) {
                    for (c = 0; c < COLUMN; c++) {
                        fprintf(output,"%c ", map[i][c]);

                    }
                    fprintf(output,"\n");
                }
                fprintf(output,"\n");
            }
        }
        else if(komutlar[v][0]==65) {/*If first character of line is "A" execute attack command*/
            if(komutlar[v][7]==77){/*Attack of monsters*/
                for(g=0;g<sayac;g++){
                    if(data[g].type[0]==77 ) {/*Take character from data according to its type if it is monster then attack*/

                        /*Take rows and columns of that monster from data array*/
                        satiri=data[g].satir;
                        sutunu=data[g].sutun;

                        /*Make attack order according to row and column*/
                        int attackorder[8][2]={{(satiri-1),sutunu},{satiri-1,sutunu+1},{satiri,sutunu+1},{satiri+1,sutunu+1},{satiri+1,sutunu},{satiri+1,sutunu-1},{satiri,sutunu-1},{satiri-1,sutunu-1}};

                        for(n=0;n<8;n++){
                            if(attackorder[n][0]>=0 &&attackorder[n][1]>=0 && attackorder[n][0]<ROW && attackorder[n][1]<COLUMN){
                                if(map[attackorder[n][0]][attackorder[n][1]] !=46){/*Check if there is a character on that position*/
                                    for(h=0;h<sayac;h++){
                                        if(data[h].name[0]==map[attackorder[n][0]][attackorder[n][1]]){
                                            if(data[h].type[0]!=77 && data[g].hp>0) {/*Check type of that character.Only attack if it is hero*/
                                                if (data[g].hp > 0) {/*Check hp of that character.Only attack if it is alive*/
                                                    data[h].hp = data[h].hp - data[g].damage;
                                                    if (data[h].hp <= 0) {
                                                        map[data[h].satir][data[h].sutun] = 46;
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                fprintf(output,"MONSTER ATTACKED\n\n");
                for(yo=0;yo<sayac;yo++){

                }
            }

            else{/*Attack of heroes*/
                for(o=0;o<sayac;o++){
                    if(data[o].type[0]==72) {/*Take character from data according to its type if it is hero then attack*/
                        satiri=data[o].satir; /*Take rows and columns of that hero from data array*/
                        sutunu=data[o].sutun;

                        /*Make attack order according to row and column*/
                        int attacksirasi[8][2]={{(satiri-1),sutunu},{satiri-1,sutunu+1},{satiri,sutunu+1},{satiri+1,sutunu+1},{satiri+1,sutunu},{satiri+1,sutunu-1},{satiri,sutunu-1},{satiri-1,sutunu-1}};

                        for(f=0;f<8;f++){
                            if(attacksirasi[f][0]>=0 && attacksirasi[f][1]>=0 &&attacksirasi[f][0]<ROW&&attacksirasi[f][1]<COLUMN){
                                if(map[attacksirasi[f][0]][attacksirasi[f][1]] !=46){/*Check if there is a character on that position*/
                                    for(h=0;h<sayac;h++){
                                        if(data[h].name[0]==map[attacksirasi[f][0]][attacksirasi[f][1]]){
                                            if(data[h].type[0]!=72 && data[o].hp > 0){/*Check type of that character.Only attack if it is monster*/
                                                data[h].hp=data[h].hp-data[o].damage;
                                                if(data[h].hp<=0){
                                                    map[data[h].satir][data[h].sutun]=46;
                                                    data[o].xp+=1;
                                                }

                                            }

                                        }

                                    }
                                }
                            }
                        }
                    }
                }
                fprintf(output,"HEROES ATTACKED\n\n");

            }

            /*Check if all monsters and heroes are dead or alive if */

            for(a=0;a<sayac;a++) {
                if (data[a].type[0] == 72) {
                    if (data[a].hp <= 0) {/*Check hp of heroes*/
                        sedef++;
                        if (sedef == herosayac) {

                            fprintf(output,"ALL HEROES ARE DEAD.");/*If all heroes are dead go to end of the function*/
                            goto end;
                        }
                    }
                }
            }
            sedef=0;
            for(yo=0;yo<sayac;yo++){
                if(data[yo].type[0]==77){
                    if(data[yo].hp<=0){/*Check hp of hmonsters*/
                        sedef2++;
                        if(sedef2==monstersayac){
                            fprintf(output,"ALL MONSTERS ARE DEAD.");/*If all monsters are dead go to end of the function*/
                            goto end;
                        }
                    }
                }
            }
            sedef2=0;

        }


        else{/*Execute move command*/

            /*Split line into token by space and store to an  char array*/
            token3 = strtok(komutlar[v], " ");

            while (token3 != NULL) {

                arr4[t]=malloc(strlen(token3)+1);
                strcpy(arr4[t],token3);
                token3 = strtok(NULL," ");
                t++;

            }
            if(arr4[1][0]==72){
                fprintf(output,"HEROES MOVED\n");
            }
            else{
                fprintf(output,"MONSTERS MOVED\n");
            }
            for (u = 2; u < t; u = u + 3) {/*arr[u] is name */

                x = u + 1;
                w = u + 2;
                row4 = atoi(arr4[x]); /*New row and column of that name*/
                column4 = atoi(arr4[w]);

                if (row4 >= ROW || column4 >= COLUMN) {/*If that row and column out of bounds of the map*/
                    fprintf(output,"%s can't move. There is wall.\n", arr4[u]);
                }
                else if (map[row4][column4] != 46) {/*If there is a character on that row and column on map*/
                    fprintf(output,"%s can't move. Place is occupied.\n",arr4[u]);
                }
                else
                {
                    for(z=0;z<sayac;z++) {
                        if (strcmp(data[z].name, arr4[u]) == 0) {/*Check if that character dead or alive*/

                            if (data[z].hp <= 0) {
                                /*If it is dead*/
                                fprintf(output,"%s can't move. Dead.\n",arr4[u]);
                            }
                            else {
                                map[data[z].satir][data[z].sutun]=46;/*If it is alive change that character's initial position to point on map*/
                                map[row4][column4] = arr4[u][0];/*Change point to character's name first letter on map according to row4 and column4 */
                                data[z].satir = row4;
                                data[z].sutun = column4;
                            }
                        }
                    }
                }

            }

            t=0;
            fprintf(output,"\n");
        }
    }


    end:/*Free allocated memory of dynamic arrays and close files*/
    free(data);
    free(veri);
    free(veri2);
    free(arr2[j]);
    free(arr3[m]);
    free(komutlar[h]);
    free(arr4[t]);
    for(i=0;i<ROW;i++){
        free(map[i]);
    }
    free(map);
    fclose(chars);
    fclose(command);
    fclose(output);

    return 0;
}

