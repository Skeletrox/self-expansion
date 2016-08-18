/*Counter and timer. Set the number of times you want to say the mantra.
After each iteration, press any key and the computer beeps, the counter increments and is displayed on the screen.
Once you hit the limit, no beeps will sound when you press the key because Linux doesn't have a predefined, portable Beep() or getch() defined.
I had to get the code for getch() from an external source actually
Then the program displays the amount of time an average iteration took [so you can tell your father you did not say it too fast! :p]
*/

#include <iostream>
#include <stdio.h>
#include <time.h>
#include <termios.h>

using namespace std;

static struct termios old, nouveau;

//Initialize new terminal i/o settings 
void initTermios (int echo)
{
	tcgetattr (0, &old); 			//Grab old terminal i/o settings
	nouveau = old; 				//Make new settings same as old settings
	nouveau.c_lflag &=~ICANON; 		//Disables buffered i/o
	nouveau.c_lflag &= echo? ECHO : ~ECHO;	//Set echo mode
	tcsetattr (0, TCSANOW, &nouveau); 	//Use the new terminal i/o settings
}

//Restore old terminal i/o settings
void resetTermios()
{
	tcsetattr(0, TCSANOW, &old);
}

//Reads a character and sets echo mode to 0
char getch()
{
	char ch;
	initTermios(0);
	ch = getchar();
	resetTermios();
	return ch;
}

int main()
{
    int val=0, MAX, i, flag=0;    //turns out max was a keyword. Hmm.
    char st;
    time_t start, finish;
    double avg;             //Answers the famous "How fast do you say your mantras???" question.
    cout << "\nEnter max value:\t";
    cin >> MAX;
    cout << "\nPress s to start timing" << endl;;
    while (!flag)
    {
	    st = getch();
	    if (st=='s')
		    flag=1;
    }
    cout << "Timing starts now" << endl;
    start = time(NULL);
    while (val<MAX)
    {
        while(getch())
        {
            val++;
            cout << val;
            cout << "\a";
            cout <<"\t";
            break;
        }
    }
    finish = time(NULL);
    avg = (double) difftime(finish, start)/MAX;
    cout << "\nMax value achieved\n";
    cout << "\nAverage time per iteration is " << avg << " seconds" << endl;
    return 0;
}
