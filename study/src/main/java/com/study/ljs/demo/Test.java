package com.study.ljs.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {

        System.out.println(isNumber2("01234567"));
    }

    public static boolean isNumber2(String s) {

        Map[] maps = {
                new HashMap(4) {{
                    put(Event.Blank, State.ZERO);
                    put(Event.Sign, State.ONE);
                    put(Event.Number, State.TWO);
                    put(Event.Dot, State.FOUR);
                }},
                new HashMap(2) {{
                    put(Event.Dot, State.FOUR);
                    put(Event.Number, State.TWO);
                }},
                new HashMap(4) {{
                    put(Event.Blank, State.EIGHT);
                    put(Event.Dot, State.THREE);
                    put(Event.Ee, State.FIVE);
                    put(Event.Number, State.TWO);
                }},
                new HashMap(3) {{
                    put(Event.Number, State.THREE);
                    put(Event.Ee, State.FIVE);
                    put(Event.Blank, State.EIGHT);
                }},
                new HashMap(1) {{
                    put(Event.Number, State.THREE);
                }},
                new HashMap(2) {{
                    put(Event.Number, State.SEVEN);
                    put(Event.Sign, State.SIX);
                }},
                new HashMap(1) {{
                    put(Event.Number, State.SEVEN);
                }},
                new HashMap(2) {{
                    put(Event.Number, State.SEVEN);
                    put(Event.Blank, State.EIGHT);
                }},
                new HashMap(1) {{
                    put(Event.Blank, State.EIGHT);
                }}};
        Tu tu = new Tu();
        tu.setRelationship(maps);
        return tu.start(s);
    }
}

enum State {
    //    装填
    NoState(-1),
    ZERO(0),
    ONE(1),
    TWO(2,true),
    THREE(3,true),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7,true),
    EIGHT(8,true),
    NINE(9);


    int code;
    boolean isEnd;

    State(int code) {
       this.code = code;
       this.isEnd = false;
    }
    State(int code,boolean isEnd) {
        this.code = code;
        this.isEnd = isEnd;
    }

    public static State getStateByCode(int code){
        for (State s:State.values()) {
            if (code==s.code){
                return s;
            }
        }
        return State.NoState;
    }
}

class Tu {

    private Map<State, Map<Event, State>> relationship;

    public Tu(Map<State, Map<Event, State>> relationship) {
        this.relationship = relationship;
    }

    public Tu() {
        this.relationship = new HashMap<>();
    }

    public Map<State , Map<Event, State>> getRelationship() {
        return this.relationship;
    }

    public void setRelationship(Map<State,Map<Event,State>> maps) {
        this.relationship = maps;
    }
    public void setRelationship(Map<Event,State>[] maps) {
        for (int i = 0; i <maps.length ; i++) {
            this.relationship.put(State.getStateByCode(i),maps[i]);
        }
    }


    public boolean start(String s){
        char[] chars = s.toCharArray();

        State curStart = State.ZERO;

        for (char ch:chars) {
//            当前事件
            Event eventByCharacter = Event.getEventByCharacter(ch);
            if (eventByCharacter==Event.NoEvent) {
                return false;
            }
//            触发事件之后改变的状态
            curStart = this.relationship.get(curStart).getOrDefault(eventByCharacter, State.NoState);
            if (curStart==State.NoState){
                return false;
            }
        }
        return curStart.isEnd;
    }

}


enum Event {

    Number('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Dot('.'),
    Blank(' '),
    Sign('+', '-'),
    Ee('e', 'E'),
    NoEvent('?');

    private Set<Character> characterSet;

    Event(Character... characters) {
        characterSet = new HashSet<>(characters.length);
        for (Character c : characters) {
            characterSet.add(c);
        }
    }

    public static Event getEventByCharacter(char c) {
        for (Event event : Event.values()) {
            if (event.characterSet.contains(c)) {
                return event;
            }
        }
        return Event.NoEvent;
    }
}