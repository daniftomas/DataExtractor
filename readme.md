# Exercise 5

Given the example JSON below, devise the necessary code in any language to receive a JSON containing data and another JSON with deﬁnitions to extract pieces of data.

The algorithm should be prepared to perform the extraction of the value(s) based in a JSON input, using the following operations:

⁃ {"paths": "dot.notation.path.1.to.property"} -> outputs a single value ⁃ {"paths": [ "path.to.ﬁrst.value", "path.to.second.value" ], "delimiter": "_"} > outputs two values separated by underscore, i.e. abc_xyz ⁃ {"paths":[["path.to.main.value", "path.to.alternative.value"]], "delimiter": "_"} -> outputs one value or its alternative, in case the ﬁrst one was not found in the Data. i.e. 123 or 345


---
## Build
Before running the program, execute the command:
`mvn compile`

## Running
Execute the command:
`mvn exec:java`

## Example path cases

Data Json Path: test_data/data.json

Config Json Path 1: test_data/extractors/first_training_name.json

Config Json Path 2: test_data/extractors/internal_id_and_gross_salary.json

Config Json Path 3: test_data/extractors/internal_issues_or_list_of_parties.json --> not implemented, wont work