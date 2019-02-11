#include "dominion.h"
#include <stdlib.h>
#include <stdio.h>
#include "assert.h"
#include "rngs.h"
#include <time.h>

//whoseTurn() unit test
int main(){
    struct gameState game;
    int currentTurn = 0;
    int s = 0;
    for (int i = 0; i < 15; i++){
        game.whoseTurn = currentTurn;
        s = whoseTurn(&game);
        assert(s == currentTurn);
        currentTurn++;
    }
    printf("Test: whoseTurn() passed\n");
    return 0;
}
