import java.util.*;
import java.io.*;

public class Hangman {

    static int mode;
    public static void print_file(String name)throws FileNotFoundException {         //Prints a file
       File file = new File(name);
       try {
           Scanner sc = new Scanner(file);
           while (sc.hasNextLine()) {
               String line = sc.nextLine();
               System.out.println(line);
           }
           sc.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
    }



    public static void main(String[] args) throws FileNotFoundException{
        print_file("data/hangman.txt");                                  //Hangman sign
        System.out.println("");
        print_file("data/instructions.txt");                             //Instructions of the game
        BaseGame.play();
    }
}
/*

frames = open('../data/phases.txt').read().split('nextscene\n')            //Hanging phases

rword = random.choice(open('../data/adjectives.txt').read().split('\n'))
System.out.println(rword);
String key = '_ '*(rword.length()-1)+'_'
print(key)
letters = []


def clear(s):
    print (s)
    time.sleep(1); os.system('cls')


while True:
    print (frames[0] % (str(letters).replace('\'', ''), key))
    if key == rword:
        print ('YOU WIN!'); break
    if len(frames) == 1:
        print ('YOU LOSE!'); break
    try:
        letter = input()
    except:
        clear('That is not valid input!'); continue
    if letter not in [l for l in string.lowercase]:
        clear('That is not valid input!'); continue
    if letter in letters:
        clear('That letter was already tried!'); continue
    letters.append(letter)
    if letter in rword:
        key = [t for t in key]; c = 0; n = []
        for a in rword:
            if a == letter:
                n.append(c)
            c += 1
        for b in n:
            key[b] = rword[b]
        key = ''.join(key)
        clear('That letter is in the word!'); continue
    del frames[0]
    clear('That letter is not in the word!')
*/