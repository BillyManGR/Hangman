import java.util.*;
import java.io.*;

public class Hangman {
    int mode;

   public static void print_file(String name)throws FileNotFoundException {
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

    public void play(){
        mode = mode_selection();                                                    //Select mode (checked)
        if (mode == 1) {
            System.out.println("Calling 1 Player mode");//one_player();
        } else if (mode == 2) {
            System.out.println("Calling 2 Player mode");//two_players();
        }
    }

    public int mode_selection() {
        while (true) {
            System.out.print("Selection> ");
            Scanner scanner = new Scanner(System.in);
            String selection = scanner.nextLine();
            //System.out.println("Selection = "+selection);
            if (selection == "1") {
                System.out.println("Selected mode is 1 Player");
                return 1;
            } else if (selection == "2") {
                System.out.println("Selected mode is 2 players");
                return 2;
            } else
                System.out.println("Invalid selection. Please enter '1' for 1-Player, '2' for 2-Players mode.");
        }
    }

    public static void main(String[] args) throws Exception{
        print_file("data/hangman.txt");                                  //Welcome sign
        System.out.println("");
        print_file("data/instructions.txt");                             //Instructions of the game
        //this.play();
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