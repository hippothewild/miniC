// mini-c odd magic square implementation
// Author Jihwan Chun
// To test this program, please remove the comment before compiling the code.

int mod(int dividend, int divisor) {
    return dividend - ((dividend / divisor) * divisor);
}

int magic_square(){
    int i, j, cnt, limit;
    int n;

    scanf(n);

    i = 0;
    j = n / 2;
    cnt = 1;
    limit = n * n;

    while (cnt <= limit) {
        printf((i*n)+j+1);

        if (mod(cnt, n) == 0) {
            i = i + 1;
        } else {
            i = i - 1;
            j = j + 1;
        }

        if (i < 0) i = n - 1;
        if (j < 0) j = n - 1;
        if (i == n) i = 0;
        if (j == n) j = 0;

        cnt = cnt + 1;
    }
}

int main(){
    magic_square();
    return 0;
}
