#include "dominion.h"
#include "assert.h"
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>

//Smithy Card Test
int main (int argc, char** argv) {
    
    struct gameState game;
    int choiceA = 0, choiceB = 0, choiceC = 0;
    int tmp;
    int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse,
        sea_hag, tribute, smithy};
    initializeGame(2, k, 2, &game);
    tmp = numHandCards(&game);
    cardEffect(smithy, choiceA, choiceB, choiceC, &game, NULL);
    assert(numHandCards(&game) == tmp + 2);
    printf("Smithy test passed: 3 cards drawn.\n");
    return 0;
    
}
