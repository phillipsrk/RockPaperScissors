public class RockPaperScissors {
    public static void main(String[] args){
        int ourLastMove = Integer.parseInt(args[0]);
        int theirLastMove = Integer.parseInt(args[1]);

        saveOpponentDecision(theirLastMove);
        saveOurDecision(ourLastMove);
        //rock = 0
        //paper = 1
        //scissors = 2

        System.exit(finalDecision(opponentFunction()));
    }

    public static void saveOpponentDecision(int opponentDecision){

    }

    public static void saveOurDecision(int ourDecision){

    }

    public static int opponentFunction(){
        int predicted = 0;
        double counter = 0;
        //NEED TO FIGURE OUT HOW TO FIND TOTAL ENTRIES IN FILE
        double total;
        double rockValue = 0, paperValue = 0, scissorValue = 0;

        while (file1.hasNext()){
            int move = file1.next();
            counter++;

            if(counter/total <= 0.2){
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
            else if(counter/total <= 0.4 && counter/total > 0.2){
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
            else if(counter/total <= 0.6 && counter/total > 0.4){
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
            else if(counter/total <= 0.8 && counter/total > 0.6){
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
            else if(counter/total <= 1 && counter/total > 0.8){
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
        double total;
        double consistencyValue = 0;
        while (file2.hasNext()){
            int move = file2.next();
            counter++;

            if(counter/total > 0.8 && move == ourMove){
                consistencyValue++;
            }
        }

        if(consistencyValue/(total/5) > 0.9){
            ourMove = predictedOpponentDecision;
        }
        return ourMove;
    }
}
