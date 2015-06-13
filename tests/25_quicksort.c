// mini-c Quick sort implementation
// Referenced from one of the student in CS420
// To test this program, please remove the comment before compiling the code.

int array[10];

int partition(int l, int r) {
    int pivot, i, j, t, dd;
    pivot = array[l];
    i = l;
    j = r;
    dd = 1;
    while(dd)
    {
        int aa;
        aa = 1;
        do{
            i = i + 1;
            if( i > r ){
                aa = 0;
            }
            else if( array[i] > pivot){
                aa = 0;
            }
        } while(aa);

        do{
            j = j - 1;
        } while( array[j] > pivot );


        if( i >= j ){
            dd = 0;
        }
        else{
            t = array[i];
            array[i] = array[j];
            array[j] = t;
        }
    }
    t = array[l];
    array[l] = array[j];
    array[j] = t;
    return j;
}


int quickSort(int l, int r)
{
    int j;

    if( l < r )
    {
        j = partition(l, r);
        quickSort(l, j);
        quickSort(j+1, r);
    }

    return 0;
}

int main(){
    int i;

    array[0] = 9;
    array[1] = 8;
    array[2] = 3;
    array[3] = 7;
    array[4] = 5;
    array[5] = 6;
    array[6] = 2;
    array[7] = 3;
    array[8] = 0;
    array[9] = 1;

    i = 0;
    do{
        printf(array[i]);
        i = i + 1;
    }
    while(i < 10);

    quickSort(0, 9);

    for(i = 0; i < 10 ; i = i + 1){
        printf(array[i]);
    }
    return 0;
}
