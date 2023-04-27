# In-Memory Music Store

## Description
This creates an in-memory store for recently played songs that can accommodate N songs per user, with a fixed initial capacity. 
This store has the capability to store a list of song-user pairs, with each song linked to a user. 
It fetches recently played songs based on the user and eliminates the least recently played songs when the store becomes full.

## Getting Started

### Pre-requisite
1. Need Java installed on the system
2. Need IDE (Eclipse, Intellij) installed on the system

### Installing

1. Clone the project from here - https://github.com/TheOnlyRoshan/TestVagrant
2. Use this ssh command on your terminal/cmd - git clone git@github.com:TheOnlyRoshan/TestVagrant.git
3. No need to build the code
4. After successful pull, Go to src>main>SongPlaylist and run the class

### How to run
1. The method getRecentlyPlayedSongs(int user) gets the list of songs recently played, user is provided in param
2. The method addSong(int song, int user) adds song in the memory, where param - song is for songName and param - user is for the corresponding user
