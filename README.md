# aimusicmaker
Important links:
 - https://app.zenhub.com/workspaces/ai-music-maker-5dc1d6b892bb690001d284d7/board?repos=219839242
 - https://app.slack.com/client/TQ68N9FTN/CQ8UGC77G
 - https://onlinesequencer.net/
 - https://docs.google.com/document/d/1e-882TZ_neiO5N_q6_fY1V03pR4Fn52USzog55NRjVQ/edit?usp=sharing

# Agent Environment Description:
- Performance Measure: Creates a musical track that is subjectively “better” than a random agent can produce
- Environment: Different instruments, notes, chords, beats per minute; all compiled in http://onlinesequencer.net
- Actuators: Putting notes and chords in a particular order, layering tracks
- Sensors: Knowledge of chords, reading peaks and valleys in another track, knowledge of simple music theory

- Fully observable - the agent will always start with an empty track, and would be aware of any notes that have been added at any point
- Single agent - a single agent will be creating the music. Later on, we might try creating multiple agents that work together as a band.
- Multi-agent - multiple agents representing different instruments work together to make a track (v2)
- Deterministic vs stochastic - anything in the environment is based on the agent so it makes it deterministic
- Episodic vs sequential - could be episodic if the agent is making decisions on its own (v1). Has the possibility to be sequential progression with a given task (v2), but episodic in each task that produces a track. 
- Static vs dynamic -  Static, the music file/track isn’t randomly changing
- Known vs unknown - Known since the results for all actions are known to the agent.
-  Discrete vs continuous -  discrete since finite possibilities are accessible 


# Expert Knowledge Base:
 -We store a database of expert-based knowledge from a wide range of sources, including human input and advanced information about the field, and use AI decision making to access and apply at appropriate times.
 
 -https://searchcio.techtarget.com/definition/knowledge-based-systems-KBS
 
 -https://searchenterpriseai.techtarget.com/definition/neural-network

-A very useful technique applied in a wide variety of fields- for example, healthcare. MYCIN, for example, was an early knowledge-based system used to help doctors diagnose medical issues. Particularly useful because human expert knowledge is limited and temporary, while a knowledge-based system can synthesize information from all over and remain longer.

-Applied to music theory, we can store knowledge about common chords and chord progressions, as well as melodies to create a listenable song.

-https://www.musictheory.net/lessons/57

-https://www.hooktheory.com/blog/i-analyzed-the-chords-of-1300-popular-songs-for-patterns-this-is-what-i-found/

