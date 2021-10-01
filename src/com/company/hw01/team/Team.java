package com.company.hw01.team;

public class Team {
    String teamName;
    Athlete[] teamMember = new Athlete[4];

    public Team(String teamName, Athlete[] teamMember){
        this.teamName = teamName;
        this.teamMember = teamMember;
    }

    public String getTeamName() { return teamName; }

    public Athlete[] getTeamMember() {
        return teamMember;
    }

    public void showMembers(){
        for(Athlete a: teamMember){
            a.showMembers();
        }
    }

    public void showMembersFinishedCourse(){
        for (Athlete a: teamMember){
            if(a.isOnDistance())
                a.showResult();
        }
    }
}
