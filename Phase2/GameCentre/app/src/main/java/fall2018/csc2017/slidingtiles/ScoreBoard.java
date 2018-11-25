package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreBoard implements Serializable {

    private String tempName = Character.toString('a');

    /**
     * this is a ArrayList of all records of Complexity 1
     */
    private ArrayList<Record> recordsComplexity1 = new ArrayList<>();

    /**
     * this is a ArrayList of all records of Complexity 2
     */
    private ArrayList<Record> recordsComplexity2 = new ArrayList<>();

    /**
     * this is a ArrayList of all records of Complexity 3
     */
    private ArrayList<Record> recordsComplexity3 = new ArrayList<>();

    /**
     * get the current complexity  the game.
     */
    private int currComplexity;


    /**
     * Return return a ArrayList records which corresponding to the current game complexity.
     *
     * @param complexity the complexity the game
     * @return return a ArrayList which corresponding to the current game complexity.
     */
    private ArrayList<Record> getComplexityRecords(int complexity) {
        if (complexity == 3) {
            return recordsComplexity1;
        }
        if (complexity == 4) {
            return recordsComplexity2;
        } else {
            return recordsComplexity3;
        }
    }


    /**
     * Modify ArrayList records which corresponding to the current game complexity(add a record
     * in it)
     *
     * @param record a record
     */
    void addNewRecords(Record record) {
        int complexity = record.getComplexity();
        updateComplexity(complexity);

        // get the ArrayList records which corresponding to the current game
        // complexity(add a record)
        ArrayList<Record> currentRecords = getComplexityRecords(currComplexity);

        // add or replace the record
        int temp = checkRecordIndex(record);
        if (temp == -1) {
            currentRecords.add(record);
        } else if (currentRecords.get(temp).checkLowerScore(record)) {
            currentRecords.remove(temp);
            currentRecords.add(record);
        }
        sortRecords();
    }


    /**
     * Modify and sorted the ArrayList records which corresponding to the current game complexity
     */
    private void sortRecords() {
        Collections.sort(getComplexityRecords(currComplexity));
    }


    /**
     * Return a ArrayList that contains the top five record if possible
     *
     * @return a ArrayList that contains the top five record if possible
     */
    private ArrayList<Record> getTopFive() {

        ArrayList<Record> result = new ArrayList<>();

        int i = 1;
        while (i <= getComplexityRecords(currComplexity).size() && i <= 5) {
            result.add(getComplexityRecords(currComplexity).get(i - 1));
            i++;
        }
        return result;
    }


    /**
     * Return a ArrayList that contains the String type of the top five record if possible.
     *
     * @return Return a ArrayList that contains the String type of the top five record if possible,
     * if not possible then show "Not enough users to get a fully rank" instead.
     */
    ArrayList TopFiveToString() {
        ArrayList<Record> topFive = getTopFive();
        ArrayList<String> result = new ArrayList<>();

        for (Record r : topFive) {
            result.add(r.recordToString(tempName));
        }

        if (result.size() < 5) {
            int diff = 5 - result.size();
            while (diff > 0) {
                String temp = "Not enough users to get a full rank";
                result.add(temp);
                diff--;
            }
        }
        return result;
    }


    /**
     * Modify the current complexity of the game.
     *
     * @param complexity the current complexity of the game.
     */
    private void updateComplexity(int complexity) {
        this.currComplexity = complexity;
    }


    /**
     * Return the index of the record's user's best record in the corresponding
     * complexity records ArrayList.
     *
     * @param myRecord the current record.
     * @return the index of the record's user's best record in the corresponding
     * complexity records ArrayList.
     */
    int getMyBestRank(Record myRecord) {
        ArrayList<Record> records = getComplexityRecords(myRecord.getComplexity());
        int i = 1;
        int result = 0;
        for (Record r : records) {
            if (r.getUserName().equals(myRecord.getUserName())) {
                result = i;
            }
            i++;
        }
        return result;
    }


    /**
     * Return -1 if the record is not in the corresponding complexity records ArrayList, or
     * return the index of the record's user's record.
     *
     * @param record a record
     * @return -1 if the record is not in the corresponding complexity records ArrayList, or
     * return the index of the record's user's record.
     */
    private int checkRecordIndex(Record record) {
        ArrayList<Record> tempRecord = getComplexityRecords(currComplexity);
        for (Record r : tempRecord) {
            if (r.getUserName().equals(record.getUserName())) {
                return tempRecord.indexOf(r);
            }
        }
        return -1;
    }

}












