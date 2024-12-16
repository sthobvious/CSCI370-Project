public class patient {
    private double[] features = new double[11];
    private int sd; // presence of Sleep Disorder or not
    private int id; // id for each patient

    public patient(double[] featureArr, int newsd) {
        this.features = featureArr;
        this.sd = newsd;
    }

    public patient(String dataTemp[]) {// for imputation
        for (int i = 0; i < dataTemp.length; i++) {
            if (i == 0) {
                try {
                    id = Integer.parseInt(dataTemp[i]);
                } catch (NumberFormatException e) {
                    this.attemptConversion(dataTemp, i);
                }
            } else if (i == 12) {
                try {
                    sd = Integer.parseInt(dataTemp[i]);
                } catch (NumberFormatException e) {
                    this.attemptConversion(dataTemp, i);
                }
            } else {
                try {
                    features[i - 1] = Double.parseDouble(dataTemp[i]);
                    if (Double.parseDouble(dataTemp[i]) < 0) {
                        features[i - 1] = -1;
                    }
                } catch (NumberFormatException e) {
                    this.attemptConversion(dataTemp, i);
                }
            }
        }
        /*
         * for (int j = 0; j < (dataTemp.length); j++) {
         * if (j == 0){
         * System.out.println(ID);
         * }
         * else if (j == 12) {
         * System.out.println(label);
         * }
         * else {
         * System.out.println(features[j-1]);
         * }
         * }
         */
    }

    public void attemptConversion(String dataTemp2[], int index) {
        if (index == 1) {
            if (dataTemp2[index].equalsIgnoreCase("male") == true) {
                features[index - 1] = 1;
            } else if (dataTemp2[index].equalsIgnoreCase("female") == true) {
                features[index - 1] = 0;
            } else {
                features[index - 1] = -1;
            }
        } else if (index >= 2 && index <= 6) {
            features[index - 1] = -1;
        } else if (index == 7) {
            if (dataTemp2[index].equalsIgnoreCase("normal")
                    || dataTemp2[index].equalsIgnoreCase("normal weight") == true) {
                features[index - 1] = 0;
            } else if (dataTemp2[index].equalsIgnoreCase("overweight") == true) {
                features[index - 1] = 1;
            } else if (dataTemp2[index].equalsIgnoreCase("obese") == true) {
                features[index - 1] = 2;
            } else {
                features[index - 1] = -1;
            }
        } else if (index >= 8 && index <= 11) {
            features[index - 1] = -1;
        } else if (index == 12) {
            if (dataTemp2[index].equalsIgnoreCase("None")
                    || dataTemp2[index].equalsIgnoreCase("normal weight") == true) {
                sd = 0;
            } else if (dataTemp2[index].equalsIgnoreCase("Sleep Apnea") == true) {
                sd = 1;
            } else if (dataTemp2[index].equalsIgnoreCase("Insomnia") == true) {
                sd = 1;
            } else {
                sd = -1;
            }
        }
    }

    public double getIndex(int index) {
        return features[index];
    }

    public void setIndex(double stat, int index) {
        features[index] = stat;
    }

    public double[] getFeatures() {
        return features;
    }

    public double getspecificFeature(int index) {
        return features[index];
    }

    public int getSD() {
        return sd;
    }

    public double getID() {
        return id;
    }

    public void setID(int newID) {
        this.id = newID;
    }
    public int checkData() {
    	int missingCounter = 0;
    	for (int i = 0; i < (features.length); i++) {
    		if (features[i] == -1) {
    			missingCounter++;
    		}
    	}
    	return missingCounter;
    }
    /*
     * public double getFeatData(int feat) {
     * 
     * }
     * 
     * public void printPatient(patient patient) {
     * 
     * }
     */

}
