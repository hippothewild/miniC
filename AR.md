This document tells about the short description of AR design in Jihwan Chun's mini-C implementation.

Initializing the program
------
Parsing과 symbol table generation, type check이 완료되면 각각의 Absyn node들은 `semanticAnalysis()` 함수를 통해 구문 분석 및 machine code generation을 시작한다. 먼저 SP(Stack pointer)와 FP(Frame pointer), VR(Register)와 MEM(Memory stack)에 해당하는 공간을 정의하여 T-Machine에서 사용할 수 있게 하고, 각각의 메모리를 초기화시킨다. 이 때, Stack의 맨 아래에는 END LABEL의 주소를 가리키게 두어 프로그램이 끝나고 스택이 비워질 시 정상적으로 종료되도록 하였다.

Declaration/Using of variables
------
Variable이 선언되면, 먼저 variable의 크기(Single variable = 1, Array variable = len(arr))만큼 Stack pointer를 당겨주어 해당 주소의 공간들이 variable의 값을 담을 공간으로 사용될 수 있도록 한다. array variable의 경우는 스택 내에 할당된 공간의 가장 아래쪽 주소를 한번 더 stack에 push해줌으로써 향후 주소 참조에 용이하게 한다. 할당이 끝나면 할당된 공간의 주소(array의 경우 할당된 공간 중 가장 아래쪽 주소)를 symbol에 기록해두어 나중에 `IdExpr`을 통해 해당 variable을 호출할 시 주소를 바로 참조할 수 있게 한다. variable의 값을 사용하기 위해 레지스터로 가져오는 방법은 아래 'Execution of expression'을 참고한다.

Execution of expression
------
현재 사용하고 있는 VR의 개수를 blockIdx로 정의할 때, 모든 expression의 실행은 다음과 같은 단계를 거친다.  
(1) 현재 VR(0)의 값을 VR(blockIdx+1)에 빼놓아 VR(0)을 마음대로 사용할 수 있도록 한다. 이 때 VR(blockIdx+1)을 사용하게 된다.  
(2) Expression에 대해 semantic analysis를 함. 이 때 사용중인 VR의 개수는 blockIdx+1로 정의된다.  
(3) Semantic analysis의 결과값을 VR(0)에 저장한다.  
(4) VR(0)의 값과 VR(blockIdx+1)의 값을 적절하게 사용한 후, VR(blockIdx+1)의 값을 VR(0)으로 복원한다.  
(5) Expression 연산이 완료되었다. 다음 instruction을 실행한다.  

Function calls and returns
------
먼저, printf와 scanf는 primitive하게 정의된 함수로써 별도로 구현이 된다.  
- printf는 VR(0)의 값을 STDOUT으로 출력한다.
- scanf는 Execution of expression을 할 때와 마찬가지로 현재 VR(0)을 따로 빼놓은 후 먼저 입력받을 변수의 주소를 가져와 VR(0)에 저장한다. 주소를 구한 다음, STDIN으로 입력을 받아 입력받은 값을 MEM(VR(0)@)에 저장한다.  

사용자가 직접 정의한 함수에 대한 call은 다음과 같이 이루어진다.  
(1) 현재 사용중인 VR을 모두 Memory stack에 쌓아두어 Function frame에서 VR을 처음부터 사용할 수 있도록 한다.  
(2) Argument가 있다면, 해당 argument들의 모두 semantic analysis를 각각 완료한 후 완료한 값들을 함수에서 parameter로 받을 수 있게 stack에 순서대로 쌓아놓는다.  
(3) 함수 실행을 마친 후 돌아올 곳의 LABEL 주소와 현재 Frame Pointer를 stack에 쌓아서 return할 때 쓸 수 있게 한다.  
(4) Frame pointer를 stack pointer로 밀어준다. 함수 안에서는 Frame pointer 윗부분의 스택만을 사용하게 된다.  
(5) 해당 함수의 LABEL로 점프해주고, JMP instruction의 다음 줄에 돌아올 곳의 LABEL을 표시해둔다.  
(6) Stack pointer를 쌓아준 argument의 개수만큼 다시 내려서 원상복구를 시켜놓는다. (쌓아준 argument의 개수만큼 stack을 비운다)  
(7) (1)에서 stack으로 돌려놓았던 값들을 다시 VR로 복구시켜서 함수 실행 이전의 상태로 stack과 VR를 recover해놓는다.  
(8) Call이 완료되었다. 다음 instruction을 실행한다.  
