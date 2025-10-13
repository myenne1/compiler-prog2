var int a = 5;
var char b = 'b';
var int octal = 021;
var int hex   = 0x2A;

fun void testMultiplication() {
    var int result;
    result = a * 10;
    result = result / 2;
    result = result % 3;
}

fun void testAdditive() {
    var int result;
    result = a + 5;
    result = result - 2;
}

fun void testShift() {
    var int result;
    result = a << 1;
    result = a >> 2;
}

fun void testRelational() {
    if (a < 10) { }
    if (a > 4)  { }
    if (a <= 5) { }
    if (a >= 5) { }
}

fun void testEquality() {
    if (a == 5)   { }
    if (b != 'c') { }
}

fun void testBitwise() {
    var int result;
    result = a & 1;
    result = result | 2;
    result = result ^ 3;
}

fun void testLogical() {
    if (a == 5 && b == 'b') { }
    if (a == 0 || b != 'c') { }
}

fun void testTernary() {
    var int result;
    result = a > 10 ? 1 : 0;
}

fun int testControl() {
    var int count = 0;
    // The 'var' keyword is removed from here as per the grammar for for-loop initializers
    for (var int i = 0; i < 3; i = i + 1) {
        if (a != 5) {
            continue;
        } else {
            count = count + 1;
        }
    }
    do {
        count = count + 1;
    } while (count < 5);
    return count;
}

/* Main entry for test runner */
fun int main() {
    var int finalCount; 
    testMultiplication();
    testAdditive();
    testShift();
    testRelational();
    testEquality();
    testBitwise();
    testLogical();
    testTernary();
    
    finalCount = testControl();
    return finalCount;
}