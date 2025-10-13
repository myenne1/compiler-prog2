#include <stdio.h>

// Global variables for testing
int a = 5;
char b = 'b';
int hello[] = {};
long newLong = 1;

// octals and hexes
int octal = 021;  // 17 in decimal
int hex = 0x2A;   // 42 in decimal


void testMultiplication() {
    printf("Testing Multiplicaiton \n");

    int result;
    result = a * 10; // 5 * 10 = 50
    result = result / 2; // 50 / 2 = 25
    result = result % 3;  // 25 % 3 = 1
    printf("Multiplicative test passed.\n\n");
}

void testAdditive() {
    printf("Testing Additive Expressions \n");
    int result;

    result = a + 5; // 5 + 5 = 10
    result = result - 2; // 10 - 2 = 8
    printf("Additive test passed.\n\n");
}

void testShift() {
    printf("Testing Shift Expressions \n");
    int result;
    result = a << 1; // 5 (0101) << 1 = 10 (1010)
    result = a >> 2; // 5 (0101) >> 2 = 1 (0001)
    printf("Shift test passed.\n\n");
}

void testRelational() {
    printf("Testing Relational Expressions \n");
    if (a < 10) { printf("  a < 10: PASSED\n"); }
    if (a > 4)  { printf("  a > 4:  PASSED\n"); }
    if (a <= 5) { printf("  a <= 5: PASSED\n"); }
    if (a >= 5) { printf("  a >= 5: PASSED\n"); }
    printf("Relational test passed.\n\n");
}

void testEquality() {
    printf("Testing Equality Expressions \n");
    if (a == 5)   { printf("  a == 5:   PASSED\n"); }
    if (b != 'c') { printf("  b != 'c': PASSED\n"); }
    printf("Equality test passed.\n\n");
}

void testBitwise() {
    printf("Testing Bitwise Expressions \n");
    int result;
    result = a & 1;      // 5 (0101) & 1 (0001) = 1 (0001)
    result = result | 2; // 1 (0001) | 2 (0010) = 3 (0011)
    result = result ^ 3; // 3 (0011) ^ 3 (0011) = 0 (0000)
    printf("Bitwise test passed.\n\n");
}

void testLogical() {
    printf("Testing Logical Expressions \n");
    if (a == 5 && b == 'b') { printf("  a == 5 && b == 'b': PASSED\n"); }
    if (a == 0 || b != 'c') { printf("  a == 0 || b != 'c': PASSED\n"); }
    printf("Logical test passed.\n\n");
}

void test_expressions() {
    int result;

    // Test assignments and operators
    result = a * 10;
    result = (result + 5) / 2;
    result = result % 3;
    result = result << 2;
    result = result >> 1;

    if (result > 0 && (a == 5 || b != 'c')) {
        // This is a placeholder for future statement tests
    }
    
    result = a > 10 ? 1 : 0;
}



// --- Main Test Runner ---
int test() {
    int newInt = 0;

    testMultiplicative();
    testAdditive();
    testShift();
    testRelational();
    testEquality();
    testBitwise();
    testLogical();
    test_expressions();

    for(int i = 0; i < 10; i++) {
        if(a != 5) {
            continue;
        } else {
            newInt++;
            return newInt;
        }
    }

    return 0;
}