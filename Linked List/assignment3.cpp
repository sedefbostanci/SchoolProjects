#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <cstdlib>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

struct node_one{ /* Singly linked list*/

    string name;
    string team_name;
    struct node_two *second;
    struct node_one *next;
};

struct node_two{ /*Double linked list*/

    string away_team;
    int minute;
    int match_ID;
    struct node_two *n;
    struct node_two *prew;

};

void initNode(struct node_two *head,string awayteam,int minofgoal,int ID) /*This function for add first node to double linked list*/
{
		head->away_team = awayteam;
		head->minute = minofgoal;
		head->match_ID = ID;
		head->n= NULL;
		head->prew=NULL;

}

void addNode(struct node_two *head,string awayteam,int minofgoal,int ID){ /*This function for add node to end of double linked list*/

    node_two *current = head;

	while(current->n !=NULL) /*First find last node*/
	 {
		 current = current->n;
     }
      if (current->n == NULL) /*Then create node and point last node's next to new created node */
		 {
          node_two *newNode = new node_two;
		  current->n = newNode;
		  newNode->away_team = awayteam;
          newNode->minute = minofgoal;
          newNode->match_ID = ID;
		  newNode->n = NULL;
		  newNode->prew=current;
		 }
}

void display(struct node_two *head,ofstream& output,string name) /*Display double linked list*/
{
    node_two *cur = head;

    output<<"Matches of "<<name<<endl;

    while(cur)
    {
      output<<"Footballer Name: "<<name<<",Away Team: "<< cur->away_team<< ",Min of Goal: "<<cur->minute<<",Match ID: "<<cur->match_ID<<endl;
      cur = cur->n;
    }
}

void displaySeven(struct node_two *head,ofstream& output,string name){ /*This function for display seventh item in operations file*/

    node_two *cur = head;

    /* Check the Id if it has already printed or not*/
    while(cur){
          if(cur->prew != NULL){
               if(cur->match_ID != cur->prew->match_ID){ /* If it has not printed yet print it */
                    output<<"footballer Name: "<<name<<",Match ID: "<<cur->match_ID<<endl;
                }
          }
          else{
                output<<"footballer Name: "<<name<<",Match ID: "<<cur->match_ID<<endl;
          }
          cur = cur->n;
    }
}

void bubbleSort(struct node_two *start) /*This function for sorting match Ids in ascending order*/
{
    int swapped, i;
    struct node_two *ptr1;
    struct node_two *s = NULL;

    /* Checking for empty list */
    if (start == NULL)
        return;
    do
    {
        swapped = 0;
        ptr1 = start;

        while (ptr1->n != s)
        {
            if (ptr1->match_ID > ptr1->n->match_ID)
            {
                swap(ptr1->match_ID, ptr1->n->match_ID);
                swapped = 1;
            }
            ptr1 = ptr1->n;
        }
        s = ptr1;
    }
    while (swapped);
}


void Reverse(struct node_two* p,string name,ofstream& output,int control){ /* This function for print match Ids in descending order*/

    if(p == NULL){

        return;
    }
    Reverse(p->n,name,output,control);

    if(p->prew != NULL){
       if(p->match_ID != p->prew->match_ID){
            output<<"footballer Name: "<<name<<",Match ID: "<<p->match_ID<<endl;
        }
    }
    else{
        if(control==0){
            output<<"footballer Name: "<<name<<",Match ID: "<<p->match_ID<<endl;
        }
        else{
            output<<"footballer Name: "<<name<<",Match ID: "<<p->match_ID;
        }
    }

}

int mostScored(struct node_two *head,int goalScorer[],int counter,int hatTrick[]){ /*This function for find most scored half and name of player who had hat trick*/

    node_two *cur = head;
    int firsthalf=0,secondhalf=0,goalCounter=0,hattrick=0,value=5;
    int secondCont;

    vector<int> hatTrickCont;

    while(cur){

      /*Check all match Ids if its in first half or second half*/
      if(cur->minute<=45){
        firsthalf++;
      }
      else{
        secondhalf++;
      }

      int curId=cur->match_ID; /* Take curretn node match Id*/
      node_two *newNode=cur;

      while(newNode !=NULL){ /* Look other nodes until end of linked list*/

        /*This part for find hatTrick because hat trick should be in same match so match Ids should be same*/
        if(newNode->match_ID == curId){  /*If looking nodes match Id same as current Id increase hattrick counter*/
            hattrick++;
        }
        newNode=newNode->n;
      }

      hatTrickCont.push_back(hattrick); /*Took all number of match Ids*/
      hattrick=0;
      goalCounter++; /*This for find goal scorer player*/
      cur = cur->n;

    }

    goalScorer[counter]=goalCounter;

    for(int y=0;y<hatTrickCont.size();y++){ /*If number of a match Id is bigger or equal to 3 it means that player make hat trick in a match so in array assign its index to 1*/
        if(hatTrickCont[y]>=3){
            secondCont=1;
            hatTrick[counter]=1;
            value=1;
        }
    }

    if(value == 5){ /*If there is not 3 or more same Id assign that player index to 0 in array */
        hatTrick[counter]=0;
    }

    /*This part for find most scored half*/
    if(firsthalf>secondhalf){
            return 0;
    }
    else if(firsthalf<secondhalf){
        return 1;
    }
    else{
        return -1;
    }
}


class linked_list_one /*This class for bigger linked list*/
{
private:
    node_one *head,*tail;
public:
    linked_list_one()
    {
        head = NULL;
        tail = NULL;
    }

    void add_node_one(string isim,string teamname,struct node_two* x)/*Insert nodes at end of list*/
    {
        node_one *tmp = new node_one;
        node_two *newsecond=new node_two;

        tmp->name = isim;
        tmp->team_name = teamname;
        tmp->second=x;
        tmp->next = NULL;

        if(head == NULL){
            head = tmp;
            tail = tmp;
        }
        else{
            tail->next = tmp;
            tail = tail->next;
        }
    }

    void display_one(ofstream& output) /*Display bigger linked list*/
    {
        node_one *temp=new node_one;
        temp=head;

        while(temp!=NULL)
        {
          output<<temp->name<<endl;
          temp=temp->next;
        }
    }
    bool searchNode(string key) /*Search name of footballer */
	{
	    node_one *temp=new node_one;
        temp=head;

		while(temp)
		{
			if (temp->name == key){
		        return true;
            }
			temp = temp->next;
		}

	}
	void sorting(){ /* Sort linked list*/

        struct node_one *ptr, *s;
        string value;
        string team;

        node_two *newNode=new node_two;
        ptr = head;

        while (ptr != NULL){ /* Look for all nodes in list*/
            for (s = ptr->next;s !=NULL;s = s->next){ /* s is current nodes next node it is for comparing */
                if (ptr->name > s->name){ /*Compare next node and current nodes name */

                    /*Hold values in temp variable and change next node and current node values*/
                    value = ptr->name;
                    team=ptr->team_name;
                    newNode=ptr->second;

                    ptr->name = s->name;
                    s->name = value;

                    ptr->team_name = s->team_name;
                    s->team_name =team;

                    ptr->second = s->second;
                    s->second = newNode;
                }
            }
            ptr = ptr->next;
        }
	}
	int findScoredHalf(int goalScorer[],int hatTrick[]){ /*Find most scored half*/
        node_one *temp=new node_one;
        temp=head;
        int result;
        int firsthalf=0,secondhalf=0,counter=0;

		while(temp){

            result=mostScored(temp->second,goalScorer,counter,hatTrick);
            counter++;
            if(result==0){
                firsthalf++;
            }
            else if(result==1){
                secondhalf++;
            }
            temp=temp->next;
		}

        if(firsthalf>secondhalf){
            return 0;
        }
        else if(firsthalf<secondhalf){
            return 1;
        }
        else{
            return -1;
        }

	}
	void print(int index,ofstream& output){ /* Took index of requested player in linked list and print his name*/
	    int value, pos = 0;
        struct node_one *s;
        s = head;
        while (s != NULL)
        {
            pos++;
            if (pos == index){
                output<<s->next->name<<endl;
            }
            s = s->next;
        }
    }
    void printFunc(string key,ofstream& output,int control,int lineCont){
        node_one *temp=new node_one;
        temp=head;

		while(temp)
		{
			if (temp->name == key){
                if(control==6){ /* If requested item is 6*/
                    display(temp->second,output,temp->name); /* Print his matches*/
                }
                else{
                   printSevenEight(key,control,output,lineCont); /* If requested item is seven or eight*/
                }
            }
			temp = temp->next;
		}
    }
    void printSevenEight(string key,int control,ofstream& output,int linecont){

        node_one *temp=new node_one;
        temp=head;
        vector<int> matchID;

		while(temp){
			if (temp->name == key){

                if(control==7){ /* If requested item is 7*/
                  bubbleSort(temp->second); /* Sort index in ascending order*/
                  displaySeven(temp->second,output,temp->name); /* Print Ids*/
                }
                else if(control==8){  /* If requested item is 8*/
                  Reverse(temp->second,temp->name,output,linecont); /* Print Ids in descending order*/

                }
            }
			temp = temp->next;
		}
    }
};

vector<string> split (const string &s, char delim) { /*This function for split lines into space*/

    vector<string> result;
    stringstream ss (s);
    string item;

    while (getline (ss, item, delim)) {
        result.push_back (item);
    }

    return result;
}

bool searchTeam(string key,vector<string> teamNames){ /*This function for hold team names in a vector*/

    int i;
    for(i=0; i<teamNames.size() ; i++){
        if(teamNames[i]==key){
            return true;
        }
    }
}

int main(int argc, char **argv)
{
    const size_t SIZE = 300;
    string line1[SIZE];
    string line2[SIZE];
    string name;

    ifstream input_file(argv[1]);
    ifstream operations_file(argv[2]);
    ofstream output;

    int a =0,b=0;
    int index,x;
    int counter=0;
    vector<string> teamNames;

    linked_list_one linkedlistone;

    output.open (argv[3]);

    while(!input_file.eof() && a < SIZE) /*Open input file and store each lines in an array*/
    {
        getline(input_file,line1[a]);
        ++a;
    }

    for(int i=0;i<a;i=i+2){

       vector<string> v = split (line1[i], ','); /* Took each line an split from space*/

       name = v[0];
       node_two *head = new node_two;

       initNode(head,v[2],atoi(v[3].c_str()),atoi(v[4].c_str())); /* Insert first node to double linked list*/

       bool cont=searchTeam(v[1],teamNames);

       if(cont != true){
            teamNames.push_back(v[1]); /*Hold team names in vector*/
       }

       /*This part for create double lined list*/
       for(int j=i+2;j<a;j=j+2){
           vector<string> arr2 = split (line1[j], ',');

           if(arr2[0]==name){
              addNode(head,arr2[2],atoi(arr2[3].c_str()),atoi(arr2[4].c_str())); /* Look for all lines at end of list from current line if names are equal insert node to double linked list*/
           }
       }
        if(linkedlistone.searchNode(name)!= true){
            linkedlistone.add_node_one(name,v[1],head); /*Add created double linked list and other necessary values to bigger linked list*/
            linkedlistone.sorting(); /*This part for create linked list according to ascending order of footballers name*/
            counter++;
        }

    }

    int goalScorer[counter];
    int hatTrick[counter];

    int result=linkedlistone.findScoredHalf(goalScorer,hatTrick);

    output<<"1)THE MOST SCORED HALF"<<endl;
    output<<result<<endl;

    int maxi = goalScorer[0];

    for (int h = 0; h < counter; h++) /*Find max goal number in list*/
    {
      if (goalScorer[h] > maxi){
          maxi = goalScorer[h];
        }
    }

    output<<"2)GOAL SCORER"<<endl;
    for(int g=0; g< counter ; g++){ /* Find index of goal scorer in linked list and print his name*/

        if(goalScorer[g]==maxi){
            index=g;
            linkedlistone.print(index,output);
        }
    }

    output<<"3)THE NAMES OF FOOTBALLERS WHO SCORED HAT-TRICK"<<endl;

    for(int c=0;c<counter;c++){ /* Find index of palyer who scored hat trick in linked list and print his name*/
        if(hatTrick[c]==1){

            linkedlistone.print(c,output);
        }
    }

    output<<"4)LIST OF TEAMS"<<endl;

    for(x=0;x<teamNames.size();x++){ /* Print list of teams using vector*/
        output<<teamNames[x]<<endl;
    }

    output<<"5)LIST OF FOOTBALLERS"<<endl; /*Print names of footballers using linked list*/
    linkedlistone.display_one(output);

    if (operations_file.is_open()) { /* Open operations file and store each line to array*/
        while (getline(operations_file, line2[b])) {
            ++b;
        }
        operations_file.close();
    }

    for(int y=0;y<b;y=y+2){ /* Took asked footballers name and call print function */

        if(y==0){
            output<<"6)MATCHES OF GIVEN FOOTBALLER"<<endl;

            vector<string> operant = split (line2[y], ',');

            for(int t=0;t<operant.size();t++){
                linkedlistone.printFunc(operant[t],output,6,2);
            }
        }
        else if(y==2){

            output<<"7)ASCENDING ORDER ACCORDING TO MATCH ID"<<endl;
            vector<string> operant2 = split (line2[y], ',');
            for(int t=0;t<operant2.size();t++){
                linkedlistone.printFunc(operant2[t],output,7,2);

            }
        }
        else if(y==4){

            output<<"8)DESCENDING ORDER ACCORDING TO MATCH ID"<<endl;
            vector<string> operant3 = split (line2[y], ',');

            for(int t=0;t<operant3.size();t++){
                if(t==operant3.size()-1){
                   linkedlistone.printFunc(operant3[t],output,8,1);
                }
                else{
                    linkedlistone.printFunc(operant3[t],output,8,0);
                }
            }
        }
    }

    output.close();

    return 0;
}
