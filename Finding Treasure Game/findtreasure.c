#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int findtreasure(int **maparr,int **keyarr,int col,int row,int n,int maprow,int mapcol,FILE *output);

int main(int argc, char *argv[])
{
    /*VARIABLES*/
    char* token;
    char line[200],line2[200];
    int j=0,k=0,c=0,d=0;
    int i,y,mid,counter,r,p; /* Counter is key matrix size and mid is start point on map matrix*/
    int mapsize[2];

    FILE *key,*map,*output;

    map= fopen(argv[3], "r");  /*Open files*/
    key=fopen(argv[4], "r");
    output=fopen(argv[5],"w+");

    counter=atoi(argv[2]);  /*Take key matrix size*/

    token = strtok(argv[1], "Xx"); /*Split first argument from X to find map matrix size*/

    while( token != NULL ) { /*Take size information and put them in an array */
      mapsize[j]=atoi(token);
      token = strtok(NULL, "Xx");
      j++;
    }

    int *maparr[mapsize[0]]; /*Dynamic map array */
    for(i=0;i<mapsize[0];i++){
        maparr[i]=(int*)malloc(mapsize[1]*sizeof(int));
    }

    int *keyarr[counter]; /*Dynamic key array */
    for(y=0;y<counter;y++){
        keyarr[y]=(int*)malloc(counter*sizeof(int));
    }

    while (fgets(line, sizeof(line), map) != NULL){ /*Take lines from mapmatrix.txt split them from space character and store map array*/

        token = strtok(line, " ");

        while( token != NULL ) {
            maparr[k][j]=atoi(token);
            token = strtok(NULL, " ");
            j++;
        }
        k++;
        j=0;
    }

    while (fscanf(key, "%s", line2) != EOF){  /*Take each character from keymatrix.txt and store them to key array successively */

       if(d!=counter-1){

            keyarr[c][d]=atoi(line2);
            d++;
       }
       else{
            keyarr[c][d]=atoi(line2);
            c++;
            d=0;
       }
    }

    mid=(counter-1)/2; /*Find start point on map matrix */

    findtreasure(maparr,keyarr,mid,mid,counter,mapsize[0],mapsize[1],output); /* Call findtreasure function */

    for(r = 0; r < mapsize[0] ; r++){
        free(maparr[r]);
        free(maparr);
    }

    for(p = 0; p < counter ; p++){
        free(keyarr[p]);
        free(keyarr);
    }

    fclose(map);
    fclose(key);
    fclose(output);

    return 0;
}


int findtreasure(int **maparr,int **keyarr,int col,int row,int n,int maprow,int mapcol,FILE *output){

    int i,j,mod,next,y,v,mid,m,p,r; /* n is key matrix size col and row is first point to look */
    int a=0,x=0,s=0; /* "a" is result of multiply key matrix and obtained sub-matrix */

    mid=(n-1)/2; /* mid point of key matrix and also obtained sub-matrix */
    fprintf(output,"%d,%d:",row,col);

    int *arr= (int*)malloc((n*n)*sizeof(int)); /*Dynamic array to store the elements to be multiplied respectively on sub-matrix*/
    int *arr2= (int*)malloc((n*n)*sizeof(int)); /*Dynamic array to store the elements to be multiplied respectively on key matrix*/

    for(i=row-mid ; i<=row+mid ; i++){ /* Take elements from sub-matrix respectively and store */
        for(j=col-mid ; j<=col+mid; j++){
            arr[x]=maparr[i][j];
            x++;
        }
    }
    for(y=0;y<n;y++){ /* Take elements from key matrix respectively and store */
        for(v=0;v<n;v++){
            arr2[s]=keyarr[y][v];
            s++;
        }
    }

    for(m=0;m<n*n;m++){ /* and multiply them */
        a+= arr[m]*arr2[m]; /* a is total */
    }

    while(a<0){
        a+=5;
    }

    fprintf(output,"%d",a);
    fprintf(output,"\r\n");

    if((a%5)==0){ /* If mod is 0 stop */
        return 1;
    }
    else{
        mod=a%5;
        if(mod==1){
            if(row-n >= 0){ /* Control boundaries of map matrix */
                next=row-n; /* Look up to find next sub matrix */
                return findtreasure(maparr,keyarr,col,next,n,maprow,mapcol,output);
            }
            else{
                next=row+n; /*If next point is outside of map matrix look down */
                return findtreasure(maparr,keyarr,col,next,n,maprow,mapcol,output);
            }
        }
        else if(mod==2){
            if(row+n <= maprow){ /* Control boundaries of map matrix */
                 next=row+n; /* Look down to find next sub matrix */
                return findtreasure(maparr,keyarr,col,next,n,maprow,mapcol,output);
            }
            else{
                next=row-n; /*If next point is outside of map matrix look up */
                return findtreasure(maparr,keyarr,col,next,n,maprow,mapcol,output);
            }
        }
        else if(mod==3){
            if(col+n <= mapcol){ /* Control boundaries of map matrix */
                next=col+n; /* Look right to find next sub matrix */
                return findtreasure(maparr,keyarr,next,row,n,maprow,mapcol,output);
            }
            else{
               next=col-n; /*If next point is outside of map matrix look left */
               return findtreasure(maparr,keyarr,next,row,n,maprow,mapcol,output);
            }
        }
        else if(mod==4){
            if(col-n >= 0){ /* Control boundaries of map matrix */
               next=col-n; /* Look left to find next sub matrix */
               return findtreasure(maparr,keyarr,next,row,n,maprow,mapcol,output);
            }
            else{
               next=col+n; /*If next point is outside of map matrix look right */
               return findtreasure(maparr,keyarr,next,row,n,maprow,mapcol,output);
            }
        }

    }

    for(r = 0; r < maprow; r++){
        free(maparr[r]);
        free(maparr);
    }

    for(p = 0; p < n ; p++){
        free(keyarr[p]);
        free(keyarr);
    }

    free(arr);
    free(arr2);
    fclose(output);
}
