#include <iostream>
#include <conio.h>
#include <time.h>
#include <windows.h>
using namespace std;

int main()
{
    int val=0, MAX;         //turns out max was a keyword. Hmm.
    time_t start, finish;
    double avg;             //Answers the famous "How fast do you say your mantras???" question.
    cout << "\nEnter max value:\t";
    cin >> MAX;
    cout << "\nPress any key when ready to begin timing\n";
    while(!getch());
    start = time(NULL);
    while (val<MAX)
    {
        while(getch())
        {
            val++;
            cout << val;
            Beep(900, 250);
            cout <<"\t";
            break;
        }
    }
    finish = time(NULL);
    avg = (double) difftime(finish, start)/MAX;
    cout << "\nMax value achieved\n";
    Beep(1900, 750);
    cout << "\nAverage time per iteration is \t" << avg << " seconds" << endl;
    return 0;
}