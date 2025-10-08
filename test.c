#include <stdio.h>

int a = 5;
char b = 'b';
int hello[] = {};
long newLong = 1;

// octals and hexes
int octal = 021;
int hex = 0x2A;


int test() {

    int newInt = 0;

    for(int i = 0; i < 10; i++) {
        if(a != 5) {
            continue;
        }
        else {
            newInt++;
            
            return newInt;
        }
    }

    return 0;
    
}


