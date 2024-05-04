import json

# Read the words.txt file
with open('dictionary2.txt', 'r') as file:
    words = file.readlines()

# Strip newline characters and convert words to lowercase
words = [word.strip().lower() for word in words]

# Create a dictionary based on word lengths
word_dict = {}
for word in words:
    if not all(char.isalpha() for char in word):
        continue  # Skip words with non-alphabetic characters
    length = len(word)
    if length not in word_dict:
        word_dict[length] = []
    word_dict[length].append(word)

# Convert the dictionary to JSON
json_data = json.dumps(word_dict, indent=4)

# Write the JSON data to a new file
with open('words2.json', 'w') as json_file:
    json_file.write(json_data)

print("JSON data has been saved to 'words.json'")
