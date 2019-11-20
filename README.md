# aimusicmaker
Important links:
 - https://app.zenhub.com/workspaces/ai-music-maker-5dc1d6b892bb690001d284d7/board?repos=219839242
 - https://app.slack.com/client/TQ68N9FTN/CQ8UGC77G
 - https://onlinesequencer.net/
 - https://docs.google.com/document/d/1e-882TZ_neiO5N_q6_fY1V03pR4Fn52USzog55NRjVQ/edit?usp=sharing

# Agent Environment Description:
- Fully observable - the agent will always start with an empty track, and would be aware of any notes that have been added at any point
- Single agent - a single agent will be creating the music. Later on, we might try creating multiple agents that work together as a band.
- Multi-agent - multiple agents representing different instruments work together to make a track (v2)
- Deterministic vs stochastic - anything in the environment is based on the agent so it makes it deterministic
- Episodic vs sequential - could be episodic if the agent is making decisions on its own (v1). Has the possibility to be sequential progression with a given task (v2), but episodic in each task that produces a track. 
- Static vs dynamic -  Static, the music file/track isn’t randomly changing
- Known vs unknown - Known since the results for all actions are known to the agent.
-  Discrete vs continuous -  discrete since finite possibilities are accessible 

# Relevant Articles
- [I analyzed the chords of 1300 popular songs for patterns. This is what I found.](https://www.hooktheory.com/blog/i-analyzed-the-chords-of-1300-popular-songs-for-patterns-this-is-what-i-found/)


Sprint 11/5-11/11:
Goal: Set up environment and create a working random agent. 

 - Write up of syntax for file: John and Kerry
 - PEAS: Elon
 - External Agent Definition: Kandace
 - Interface for file: Tim
 - Random agent trials: All
