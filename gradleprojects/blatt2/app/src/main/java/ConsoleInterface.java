import java.math.BigDecimal;
import java.util.Arrays;

public class ConsoleInterface {
  public static void mainLoop(int taskNum) {
    boolean wantToExit = false;
    int index;
    BigDecimal value;
    BigDecimal[] arrayToBeAnalysed = new BigDecimal[0];
    if (taskNum == 3) {
      System.out.println(
          "Welche Spalte wollen sie Auswählen?\n(age, sex, bmi, children, smoker, charges)");
      arrayToBeAnalysed = Aufgabe3.parseCSV(UserInput.getLine());
    } else {
      arrayToBeAnalysed = Aufgabe1.createArrayFromUser();
    }
    while (true && !wantToExit) {

      System.out.println(
          "Ihr Array : "
              + Arrays.toString(arrayToBeAnalysed)
              + "\n\n"
              + "Sie haben nun folgende optionen:\n"
              + "1: Statistische Informationen erhalten\n"
              + "2: Index von bestimmtem Wert bestimmen\n"
              + "3: Entferne ein Element\n"
              + "4: Füge ein Element hinzu\n"
              + "5: Beenden\n");
      int choice = UserInput.getLineInt();
      switch (choice) {
        case 1:
          System.out.println(
              "Maximalwert: "
                  + Aufgabe1.max(arrayToBeAnalysed)
                  + "\n"
                  + "Minimalwert: "
                  + Aufgabe1.min(arrayToBeAnalysed)
                  + "\n"
                  + "Durchschnitt: "
                  + Aufgabe1.average(arrayToBeAnalysed)
                  + "\n"
                  + "Standardabweichung: "
                  + Aufgabe1.stdDeviation(arrayToBeAnalysed));
          break;
        case 2:
          System.out.println("Von welchem Wert wollen sie den Index wissen (first occurance)");
          value = UserInput.getLineBigDecimal();
          System.out.println(
              "Der Wert "
                  + value
                  + " ist an Stelle "
                  + Aufgabe1.getIndex(arrayToBeAnalysed, value)
                  + " zu finden");
          break;
        case 3:
          System.out.println("Geben sie den Index des Elementes an das sie entfernen wollen");
          index = UserInput.getLineInt();
          arrayToBeAnalysed = Aufgabe1.createArrayWithElementRemoved(arrayToBeAnalysed, index);
          break;
        case 4:
          System.out.println("Geben sie den Index an an dem sie ihr Element einfügen wollen");
          index = UserInput.getLineInt();
          System.out.println("Geben sie den Wert des Elements an");
          value = UserInput.getLineBigDecimal();
          arrayToBeAnalysed = Aufgabe1.createArrayWithElementAdded(arrayToBeAnalysed, index, value);
          break;
        case 5:
          wantToExit = true;
          break;
      }
    }
  }
}
