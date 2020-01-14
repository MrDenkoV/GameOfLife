package gol.log;

import java.util.Arrays;

public class Rules {
    States[] rules;

    public Rules(String rules){
        this.rules = new States[9];
        this.setRules(rules);
    }

    public Rules(){
        this.rules = new States[8];
        for(int i=0; i<9; i++)
            this.rules[i]=States.DIES;
    }

    public void setRules(String rules){
        for (int i=0; i<9; i++)
            this.rules[i]=States.DIES;

        int i;
        for(i=0; i<rules.length() && rules.charAt(i)!='/'; i++){
            if(rules.charAt(i)<'0' || rules.charAt(i)>'8') {
//                throw new IllegalArgumentException("Wrong Argument: " + living.charAt(i) + " not in range 0-8");
                continue;
            }
            int current = Character.getNumericValue(rules.charAt(i));
            this.rules[current]=States.SURVIVES;
        }
        i++;
        for(; i<rules.length(); i++){
            if(rules.charAt(i)<'0' || rules.charAt(i)>'8') {
//                throw new IllegalArgumentException("Wrong Argument: " + living.charAt(i) + " not in range 0-8");
                continue;
            }
            int current = Character.getNumericValue(rules.charAt(i));
            if(this.rules[current]==States.SURVIVES || this.rules[current]==States.BOTH)
                this.rules[current]=States.BOTH;
            else
                this.rules[current]=States.APPEARS;
        }
    }

    @Override
    public String toString() {
        StringBuilder wyn= new StringBuilder();

        for(int i=0; i<9; i++)
            if(this.rules[i]==States.SURVIVES || this.rules[i]==States.BOTH)
                wyn.append(String.valueOf(i));

        wyn.append('/');

        for(int i=0; i<9; i++)
            if(this.rules[i]==States.APPEARS || this.rules[i]==States.BOTH)
                wyn.append(String.valueOf(i));


        return wyn.toString();
    }
}
