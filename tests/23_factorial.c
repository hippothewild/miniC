int factorial(int x) {
    if(x == 1)
        return x;
   return x * factorial(x - 1);
}

int main(){
    printf(factorial(10));
	return 0;	
}
