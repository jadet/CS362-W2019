#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

//gameOver() unit test
int main(int argc, char** argv)
{
	struct gameState game;
	int k[10] = { adventurer, gardens, embargo, village, minion, mine, cutpurse, sea_hag, tribute, smithy };
	int a = initializeGame(2, k, 3, &game);
	int j = isGameOver(&game);
	assert(j == 0);
	assert(a == 0);
    printf("Test: gameOver() passed\n");
	return 0;
} 
