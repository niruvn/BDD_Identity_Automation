@CompareCarDetails
Feature: Compare car details on cartaxcheck website with the output file
  As a user I want to be able to extract registration numbers from a input text file
  and get the expected car identity details from outfile
  and then for each registration number, compare the actual car details from https://cartaxcheck.co.uk website
  and highlight the mismatches

  Scenario Outline: Read the car details from both input and output files, compare car details and highlight the mismatches
    Given car registration numbers to read from <inputFile>
    And read car details for each registration number from <outputFile>
    When I open website
    And Search car details for each registration number
    Then compare the results with the output file and highlight the mismatches
    Examples:
      | inputFile | outputFile |
      | car_input.txt  | car_output.txt   |
      | car_input_2.txt  | car_output_2.txt   |