#include "assert.h"
#include "dominion.h"
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>

//Village Card Test
int main (int argc, char** argv) {
    
    struct gameState game;
    int choiceA = 0, choiceB = 0, choiceC = 0;
    int tmp;
    int actions;
    int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse,
        sea_hag, steward, smithy};
    initializeGame(2, k, 2, &game);
    actions = game.numActions;
    tmp = cardEffect(village, choiceA, choiceB, choiceC, &game, NULL);
    assert(game.numActions == actions + 2);
    assert(tmp == 0);
    printf("Village test passed.\n");
    return 0;
    
}
