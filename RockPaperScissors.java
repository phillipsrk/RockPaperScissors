import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) throws IOException {
        int ourLastMove = Integer.parseInt(args[0]);
        int theirLastMove = Integer.parseInt(args[1]);

        FileWriter writer1 = new FileWriter("opponents.csv");
        writer1.append((char) theirLastMove);

        writer1.flush();
        writer1.close();

        FileWriter writer2 = new FileWriter("player.csv");
        writer2.append((char) ourLastMove);

        writer2.flush();
        writer2.close();

        System.exit(finalDecision(opponentFunction()));
    }

    public static int opponentFunction(){
        Scanner fileScan = new Scanner("opponents.csv");
        ArrayList<Integer> opponentData = new ArrayList<>();

        while(fileScan.hasNext())
        {
            opponentData.add(Integer.parseInt(fileScan.next()));
        }
        fileScan.close();

        int predicted = 0;
        double counter = 0;

        double rockValue = 0, paperValue = 0, scissorValue = 0;

        for(int j = 0; j < opponentData.size(); j++){
            int move = opponentData.get(j);
            counter++;

            if(counter/opponentData.size() <= 0.2){
                if(move == 0){
                    rockValue+= 0.2;
                }
                else if(move == 1){
                    paperValue += 0.2;
                }
                else if(move == 2){
                    scissorValue += 0.2;
                }
            }
            else if(counter/opponentData.size() <= 0.4 && counter/opponentData.size() > 0.2){
                if(move == 0){
                    rockValue+= 0.4;
                }
                else if(move == 1){
                    paperValue += 0.4;
                }
                else if(move == 2){
                    scissorValue += 0.4;
                }
            }
            else if(counter/opponentData.size() <= 0.6 && counter/opponentData.size() > 0.4){
                if(move == 0){
                    rockValue+= 0.6;
                }
                else if(move == 1){
                    paperValue += 0.6;
                }
                else if(move == 2){
                    scissorValue += 0.6;
                }
            }
            else if(counter/opponentData.size() <= 0.8 && counter/opponentData.size() > 0.6){
                if(move == 0){
                    rockValue+= 0.8;
                }
                else if(move == 1){
                    paperValue += 0.8;
                }
                else if(move == 2){
                    scissorValue += 0.8;
                }
            }
            else if(counter/opponentData.size() <= 1 && counter/opponentData.size() > 0.8){
                if(move == 0){
                    rockValue+= 1;
                }
                else if(move == 1){
                    paperValue += 1;
                }
                else if(move == 2){
                    scissorValue += 1;
                }
            }
        }

        if(rockValue < scissorValue && rockValue < paperValue){
            predicted = 0;
        }
        else if(paperValue < rockValue && paperValue < scissorValue){
            predicted = 1;
        }
        else if(scissorValue < rockValue && scissorValue < paperValue){
            predicted = 2;
        }

        return predicted;
    }

    public static int finalDecision(int predictedOpponentDecision){
        Scanner fileScan = new Scanner("player.csv");
        ArrayList<Integer> playerData = new ArrayList<Integer>();

        while(fileScan.hasNext())
        {
            playerData.add(Integer.parseInt(fileScan.next()));
        }
        fileScan.close();


        int ourMove = 0;
        if(predictedOpponentDecision == 0){
            ourMove = 1;
        }
        else if(predictedOpponentDecision == 1){
            ourMove = 2;
        }
        else if(predictedOpponentDecision == 2){
            ourMove = 0;
        }

        double counter = 0;
        double consistencyValue = 0;
        for(int j = 0; j < playerData.size(); j++) {
            int move = playerData.get(j);
            counter++;

            if(counter/playerData.size() > 0.8 && move == ourMove){
                consistencyValue++;
            }
        }

        if(consistencyValue/((playerData.size()/5)) > 0.9){
            ourMove = predictedOpponentDecision;
        }
        return ourMove;
    }
}
