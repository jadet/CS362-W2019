#include "dominion.h"
#include "dominion_helpers.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <time.h>
#include <assert.h>
#include "rngs.h"

//kingdomCards() unit test
int main(int argc, char **argv)
{
    srand(time(NULL));
    int n = 1000;
    for(int i = 0; i < n; i++){
        int j[10] = { rand(), rand(), rand(), rand(), rand(), rand(), rand(), rand(), rand(), rand() };
        int *k = kingdomCards(j[0], j[1], j[2], j[3], j[4], j[5], j[6], j[7], j[8], j[9]);
        for(int ii = 0; i < 10; i++){
            assert(j[ii] == k[ii]);
        }
    }
    printf("Test: kingdomCards() passed\n");
}
