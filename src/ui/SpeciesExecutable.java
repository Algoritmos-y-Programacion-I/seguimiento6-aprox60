package ui;

import java.util.Scanner;

import model.Fauna;
import model.Flora;
import model.Species;
import model.SpeciesController;

public class SpeciesExecutable {

    private Scanner reader;
    private SpeciesController speciesController;

    public static void main(String[] args) {
        SpeciesExecutable exe = new SpeciesExecutable();
        exe.showMainMenu();
    }

    public SpeciesExecutable() {
        reader = new Scanner(System.in);
        speciesController = new SpeciesController();
    }

    public void showMainMenu() {
        System.out.println("Welcome to Icesi Species");

        boolean stopFlag = false;

        while (!stopFlag) {
            System.out.println("\nType an option");
            System.out.println("1. Register a Species");
            System.out.println("2. Delete a Species");
            System.out.println("3. Show Species");
            System.out.println("4. Edit Species");
            System.out.println("0. Exit");

            int mainOption = reader.nextInt();

            switch (mainOption) {
                case 1:
                    registerSpecies();
                    break;
                case 2:
                    deleteSpecies();
                    break;
                case 3:
                    showSpecies();
                    break;
                case 4:
                    editSpecies();
                    break;
                case 0:
                    System.out.println("Thanks for using our system");
                    stopFlag = true;
                    break;
                default:
                    System.out.println("You must type a valid option");
                    break;
            }
        }
    }

    public void registerSpecies() {
        System.out.println("Is the species Flora or Fauna? (Type 1 for Flora, 2 for Fauna): ");
        int type = reader.nextInt();

        System.out.println("Type the new Species' name: ");
        String name = reader.next();

        System.out.println("Type the new Species' scientific name: ");
        String scientificName = reader.next();

        if (type == 1) {
            System.out.println("Does the species have flowers? (true/false): ");
            boolean hasFlowers = reader.nextBoolean();

            System.out.println("Does the species have fruits? (true/false): ");
            boolean hasFruits = reader.nextBoolean();

            System.out.println("Type the maximum height of the species: ");
            double maxHeight = reader.nextDouble();

            if (speciesController.registerFlora(name, scientificName, hasFlowers, hasFruits, maxHeight)) {
                System.out.println("Flora species registered successfully");
            } else {
                System.out.println("Error, Flora species couldn't be registered");
            }
        } else if (type == 2) {
            System.out.println("Is the species migratory? (true/false): ");
            boolean isMigratory = reader.nextBoolean();

            System.out.println("Type the maximum weight of the species: ");
            double maxWeight = reader.nextDouble();

            if (speciesController.registerFauna(name, scientificName, isMigratory, maxWeight)) {
                System.out.println("Fauna species registered successfully");
            } else {
                System.out.println("Error, Fauna species couldn't be registered");
            }
        } else {
            System.out.println("Invalid type selected.");
        }
    }

    public void deleteSpecies() {
        System.out.println("Which species do you want to delete?");
        String speciesList = speciesController.showSpeciesList();

        if (!speciesList.isEmpty()) {
            System.out.println(speciesList);
            int index = reader.nextInt() - 1;
            if (speciesController.deleteSpecies(index)) {
                System.out.println("Species deleted successfully");
            } else {
                System.out.println("Invalid option or this specie does not exist.");
            }
        } else {
            System.out.println("There aren't any species registered yet.");
        }
    }

    public void showSpecies() {
        System.out.println("Registered species:");
        String speciesList = speciesController.showSpeciesList();
        if (!speciesList.isEmpty()) {
            System.out.println(speciesList);
        } else {
            System.out.println("There aren't any species registered yet.");
        }
    }

    public void editSpecies() {
    System.out.println("Which species do you want to edit?");
    String speciesList = speciesController.showSpeciesList();

    if (!speciesList.isEmpty()) {
        System.out.println(speciesList);
        System.out.print("Select the number of the species to edit: ");
        int choice = reader.nextInt() - 1;
        reader.nextLine(); // Consume el salto de línea

        Species speciesToEdit = speciesController.getSpecies(choice);
        if (speciesToEdit != null) {
            System.out.println("Editing details for: " + speciesToEdit.getName());

            System.out.print("Enter new name: ");
            String name = reader.nextLine();

            System.out.print("Enter new scientific name: ");
            String scientificName = reader.nextLine();

            if (speciesToEdit instanceof Flora) {
                System.out.print("Does it have flowers? (true/false): ");
                boolean hasFlowers = reader.nextBoolean();

                System.out.print("Does it have fruits? (true/false): ");
                boolean hasFruits = reader.nextBoolean();

                System.out.print("Enter maximum height: ");
                double maxHeight = reader.nextDouble();

                if (speciesController.editSpecies(choice, name, scientificName, hasFlowers, hasFruits, maxHeight, null, null)) {
                    System.out.println("Flora species updated successfully!");
                } else {
                    System.out.println("Error updating flora species.");
                }

            } else if (speciesToEdit instanceof Fauna) {
                System.out.print("Is it migratory? (true/false): ");
                boolean isMigratory = reader.nextBoolean();

                System.out.print("Enter maximum weight: ");
                double maxWeight = reader.nextDouble();

                if (speciesController.editSpecies(choice, name, scientificName, null, null, null, isMigratory, maxWeight)) {
                    System.out.println("Fauna species updated successfully!");
                } else {
                    System.out.println("Error updating fauna species.");
                }
            }
        } else {
            System.out.println("Invalid selection.");
        }
    } else {
        System.out.println("There aren't any species registered yet.");
       }


    }

   
}
