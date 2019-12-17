# What is the Artificial Intelligence Music Maker (AIMM)?
We started this project with the intention of creating an AI agent that can intelligently generate music tracks that ideally sound “good”. The codebase contains an interface in which the user can select the “basic” or “advanced” agent, as well as tempo, number of bars, and starting key.

## How does it work?
There are three agents included in our system: random, basic, and advanced. The random agent simply chooses a random selection of instruments and places notes down in no particular order. 

The basic agent is a multi-agent system in which a “conductor” creates a drummer, which uses randomly selected frequencies to place down snare, kick, drum, and miscellaneous pitches. The conductor also creates a chord agent which selects chords informed by some basic knowledge of music theory from the resources class and places a chord down every measure, independently of the drummer.

Finally, the advanced agent employs a multi-agent system to allow for communication between the drummer and chord agents, unlike in the basic agent. The conductor creates a drummer and then the drummer sends abstracted information about its creation over to the chord agent which will then intelligently place down its chords, still tapping into the music theory information stored in resources.

## How to use this project
- Make Music
  - Run the public static void main in the environment class and specify what music you would like by uing our console interface, which will create a file in the project folder
  - Drag and drop the newly created file into onlinesequencer.net 
- Make your own Intelligent Agent
  - Create a class that implements AgentIF. Our agents were structured to create instrument objects, add note objects to the instruments, and call toString() to make a usable file.
  - The intelligence of the agent lies in how it decides to create Instrument and Note objects.


## Agent Environment Description:
- Performance Measure: Creates a musical track that is subjectively “better” than a random agent can produce
- Environment: Different instruments, notes, chords, beats per minute; all compiled in http://onlinesequencer.net
- Actuators: Putting notes and chords in a particular order, layering tracks
- Sensors: Knowledge of chords, reading peaks and valleys in another track, knowledge of simple music theory

- Fully observable - the agent will always start with an empty track, and would be aware of any notes that have been added at any point
- Single agent - The chord and drummer agents can produce music individually.
- Multi-agent - Using the conductor agent, the chord agent and drummer agent can work together to produce music. With the conductor agent V1, the chord and drummer agent do not listen to each other, but in V2 the chord agent takes the drummers beat into account.
- Deterministic vs stochastic - The environment is deterministic because all changes in the environment are based on the agents decisions. 
- Episodic vs sequential - could be episodic if the agent is making decisions on its own (v1). Has the possibility to be sequential progression with a given task (v2), but episodic in each task that produces a track. 
- Static vs dynamic -  Static, the music file/track isn’t randomly changing
- Known vs unknown - The environment is known because the agent has an understanding of the possibilities of the environment and a knowledge of music theory.
- Discrete vs continuous -  discrete since finite possibilities are accessible 



## Expert Knowledge Base:
- We store a database of expert-based knowledge from a wide range of sources, including human input and advanced information about the field, and use AI decision making to access and apply at appropriate times.
  - https://searchcio.techtarget.com/definition/knowledge-based-systems-KBS
  - https://searchenterpriseai.techtarget.com/definition/neural-network
- A very useful technique applied in a wide variety of fields- for example, healthcare. MYCIN, for example, was an early knowledge-based system used to help doctors diagnose medical issues. Particularly useful because human expert knowledge is limited and temporary, while a knowledge-based system can synthesize information from all over and remain longer.
- Applied to music theory, we can store knowledge about common chords and chord progressions, as well as melodies to create a listenable song.
  - https://www.musictheory.net/lessons/57
  - https://www.hooktheory.com/blog/i-analyzed-the-chords-of-1300-popular-songs-for-patterns-this-is-what-i-found/

## Takeaways and Results
- Gathering empirical data about the success of our agents is difficult due to the subjective nature of music. In order to judge the effectiveness of our advanced agents, we conducted a survey to collect data about how our agents perform.
- As a result of the same issue, creating heuristics to make the music sound “better” was also very difficult. There are not a lot of built-in rules for what sounds musically good, as this is something that varies from person to person.
- Through this project, we realized that even with a simple expert knowledge base, significant progress can be made in a complex space. Based on a survey we conducted, we found that people actually preferred a track that the basic agent made, which placed down chords without communication with the drummer agent, over the tracks generated by the advanced agent.

## Collaborators
This project was created by Tim Marotta, Kerry Anne Buckman, John Hunter, Elon Skolnik, and Kandace Richards. 

Also a special thanks to onlinesequencer.net for granting us the ability to convert text files into music!
