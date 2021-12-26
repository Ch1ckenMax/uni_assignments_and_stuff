CC = g++
FLAGS = -pedantic-errors -std=c++11 -o

MAKE_PATH = $(shell mkdir build bin 2> /dev/null)

SOURCE_DIRECTORY = ./src
OBJECT_DIRECTORY = ./build
BINARY_DIRECTORY = ./bin

BINARY_NAME = blackjack
TARGET = $(BINARY_DIRECTORY)/$(BINARY_NAME)

SOURCES = $(wildcard $(SOURCE_DIRECTORY)/*.cpp)
OBJECTS = $(patsubst $(SOURCE_DIRECTORY)/%.cpp, $(OBJECT_DIRECTORY)/%.o, $(SOURCES))

$(TARGET): $(OBJECTS)
	$(CC) $(OBJECTS) $(FLAGS) $(TARGET)

$(OBJECT_DIRECTORY)/%.o: $(SOURCE_DIRECTORY)/%.cpp
	$(MAKE_PATH)
	$(CC) -c $< $(FLAGS) $@

clean_obj:
	rm -f $(OBJECTS)

clean_bin:
	rm -f $(TARGET)

clean:
	rm -f $(OBJECTS)
	rm -f $(TARGET)
