#include "dominion.h"
#include "assert.h"
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>

//Adventurer Card Test
int main (int argc, char** argv) {
    
    struct gameState game;
    int choiceA = 0, choiceB = 0, choiceC = 0;
    int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse,
        sea_hag, tribute, smithy};
    initializeGame(2, k, 4, &game);
    assert(cardEffect(adventurer, choiceA, choiceB, choiceC, &game, 0, NULL) == 0);
    printf("Adventurer test passed.\n");
    
}
