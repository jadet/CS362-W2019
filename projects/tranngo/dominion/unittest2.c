#include "dominion.h"
#include "dominion_helpers.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <time.h>
#include <assert.h>
#include "rngs.h"

//compare() unit test
int main(int argc, char **argv)
{
    srand(time(NULL));
    int n = 1000;
    for(int i = 0; i < n; i++){
        int a = rand();
        int b = a - 1 - rand() % 1000000;
        switch(rand() % 3){
            case 0:
                assert(compare(&a, &b) == 1);
                break;
            case 1:
                assert(compare(&b, &a) == -1);
                break;
            case 2:
                assert(compare(&a, &a) == 0);
                break;
        }
    }
    printf("Test: compare() passed\n");
}
