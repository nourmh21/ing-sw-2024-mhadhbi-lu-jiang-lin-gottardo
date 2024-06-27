package it.polimi.ingsw.view.tui;

import java.util.HashMap;
import java.util.Map;

/**
 * class that provides information about cards based on id and side.
 */
public class Resource {

   static final private Map<Integer,String> card= new HashMap<>(){
        {
            put( 1,"type: RESOURCE, kingdom: FUNGI, points: 0, top_left_angle:FUNGI , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:FUNGI ");
            put( 2,"type: RESOURCE, kingdom: FUNGI, points: 0, top_left_angle:FUNGI , top_right_angle:FUNGI, bottom_right_angle:EMPTY , bottom_left_angle:HIDDEN ");
            put( 3,"type: RESOURCE, kingdom: FUNGI, points: 0, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:FUNGI, bottom_left_angle:FUNGI ");
            put( 4,"type: RESOURCE, kingdom: FUNGI, points: 0, top_left_angle:HIDDEN , top_right_angle:FUNGI, bottom_right_angle:FUNGI, bottom_left_angle:EMPTY ");
            put( 5,"type: RESOURCE, kingdom: FUNGI, points: 0, top_left_angle:HIDDEN , top_right_angle:FEATHER, bottom_right_angle:FUNGI, bottom_left_angle:PLANT ");
            put( 6,"type: RESOURCE, kingdom: FUNGI, points: 0, top_left_angle:INK_BOTTLE , top_right_angle:FUNGI, bottom_right_angle:ANIMAL, bottom_left_angle:HIDDEN ");
            put( 7,"type: RESOURCE, kingdom: FUNGI, points: 0, top_left_angle:FUNGI , top_right_angle:INSECT, bottom_right_angle:EMPTY, bottom_left_angle:PARCHMENT ");
            put( 8,"type: RESOURCE, kingdom: FUNGI, points: 1, top_left_angle:EMPTY , top_right_angle:FUNGI, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put( 9,"type: RESOURCE, kingdom: FUNGI, points: 1, top_left_angle:FUNGI , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ");
            put(10,"type: RESOURCE, kingdom: FUNGI, points: 1, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:FUNGI ");

            put(11,"type: RESOURCE, kingdom: PLANT, points: 0, top_left_angle:PLANT , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:PLANT ");
            put(12,"type: RESOURCE, kingdom: PLANT, points: 0, top_left_angle:PLANT , top_right_angle:PLANT, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(13,"type: RESOURCE, kingdom: PLANT, points: 0, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:PLANT, bottom_left_angle:PLANT ");
            put(14,"type: RESOURCE, kingdom: PLANT, points: 0, top_left_angle:HIDDEN , top_right_angle:PLANT, bottom_right_angle:PLANT, bottom_left_angle:EMPTY ");
            put(15,"type: RESOURCE, kingdom: PLANT, points: 0, top_left_angle:HIDDEN , top_right_angle:INSECT, bottom_right_angle:PLANT, bottom_left_angle:FEATHER ");
            put(16,"type: RESOURCE, kingdom: PLANT, points: 0, top_left_angle:FUNGI , top_right_angle:PLANT, bottom_right_angle:INK_BOTTLE, bottom_left_angle:HIDDEN ");
            put(17,"type: RESOURCE, kingdom: PLANT, points: 0, top_left_angle:PARCHMENT , top_right_angle:HIDDEN, bottom_right_angle:ANIMAL, bottom_left_angle:PLANT ");
            put(18,"type: RESOURCE, kingdom: PLANT, points: 1, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:PLANT ");
            put(19,"type: RESOURCE, kingdom: PLANT, points: 1, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:PLANT, bottom_left_angle:HIDDEN ");
            put(20,"type: RESOURCE, kingdom: PLANT, points: 1, top_left_angle:HIDDEN , top_right_angle:PLANT, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ");

            put(21,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:ANIMAL , top_right_angle:ANIMAL, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(22,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:ANIMAL, bottom_left_angle:ANIMAL ");
            put(23,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:ANIMAL , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:ANIMAL ");
            put(24,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:EMPTY , top_right_angle:ANIMAL, bottom_right_angle:ANIMAL, bottom_left_angle:HIDDEN ");
            put(25,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:HIDDEN , top_right_angle:INSECT, bottom_right_angle:ANIMAL, bottom_left_angle:INK_BOTTLE ");
            put(26,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:PLANT , top_right_angle:ANIMAL, bottom_right_angle:PARCHMENT, bottom_left_angle:HIDDEN ");
            put(27,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:FEATHER , top_right_angle:HIDDEN, bottom_right_angle:FUNGI, bottom_left_angle:ANIMAL ");
            put(28,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:ANIMAL ");
            put(29,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:ANIMAL, bottom_left_angle:EMPTY ");
            put(30,"type: RESOURCE, kingdom: ANIMAL, points: 0, top_left_angle:EMPTY , top_right_angle:ANIMAL, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");

            put(31,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:INSECT, top_right_angle:INSECT, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(32,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:HIDDEN, top_right_angle:EMPTY, bottom_right_angle:INSECT, bottom_left_angle:INSECT ");
            put(33,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:INSECT , top_right_angle:HIDDEN , bottom_right_angle:EMPTY , bottom_left_angle:INSECT ");
            put(34,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:EMPTY , top_right_angle:INSECT , bottom_right_angle:INSECT , bottom_left_angle:HIDDEN ");
            put(35,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:HIDDEN , top_right_angle:FEATHER , bottom_right_angle:INSECT , bottom_left_angle:ANIMAL ");
            put(36,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:PARCHMENT , top_right_angle:INSECT , bottom_right_angle:FUNGI , bottom_left_angle:HIDDEN ");
            put(37,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:INSECT , top_right_angle:PLANT , bottom_right_angle:HIDDEN , bottom_left_angle:INK_BOTTLE ");
            put(38,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:INSECT , top_right_angle:HIDDEN , bottom_right_angle:EMPTY , bottom_left_angle:EMPTY ");
            put(39,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:EMPTY , top_right_angle:EMPTY , bottom_right_angle:INSECT , bottom_left_angle:HIDDEN ");
            put(40,"type: RESOURCE, kingdom: INSECT, points: 0, top_left_angle:HIDDEN , top_right_angle:INSECT , bottom_right_angle:EMPTY , bottom_left_angle:EMPTY ");

            put(41,"type: GOLD, kingdom: FUNGI, points: 1(FEATHER), condition:2 fungi - 1 animal, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle: FEATHER, bottom_left_angle:EMPTY ");
            put(42,"type: GOLD, kingdom: FUNGI, points: 1(INK_BOTTLE), condition:2 fungi - 1 plant, top_left_angle:EMPTY , top_right_angle:INK_BOTTLE, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(43,"type: GOLD, kingdom: FUNGI, points: 1(PARCHMENT),condition:2 fungi - 1 insect,  top_left_angle:PARCHMENT , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(44,"type: GOLD, kingdom: FUNGI, points: 2(COVERED_ANGLE),condition:3 fungi - 1 animal,  top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(45,"type: GOLD, kingdom: FUNGI, points: 2(COVERED_ANGLE),condition:3 fungi - 1 plant,  top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(46,"type: GOLD, kingdom: FUNGI, points: 2(COVERED_ANGLE),condition:3 fungi - 1 insect,  top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ");
            put(47,"type: GOLD, kingdom: FUNGI, points: 3, condition:3 fungi,  top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:HIDDEN, bottom_left_angle:INK_BOTTLE ");
            put(48,"type: GOLD, kingdom: FUNGI, points: 3, condition:3 fungi, top_left_angle:FEATHER , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:HIDDEN ");
            put(49,"type: GOLD, kingdom: FUNGI, points: 3, condition:3 fungi,  top_left_angle:HIDDEN , top_right_angle:PARCHMENT, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(50,"type: GOLD, kingdom: FUNGI, points: 5, condition:5 fungi, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");

            put(51,"type: GOLD, kingdom: PLANT, points: 1(FEATHER), condition:2 plant - 1 insect, top_left_angle:FEATHER , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(52,"type: GOLD, kingdom: PLANT, points: 1(PARCHMENT), condition:2 plant - 1 fungi, top_left_angle:EMPTY , top_right_angle:PARCHMENT, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(53,"type: GOLD, kingdom: PLANT, points: 1(INK_BOTTLE), condition:2 plant - 1 animal, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:INK_BOTTLE ");
            put(54,"type: GOLD, kingdom: PLANT, points: 2(COVERED_ANGLE), condition:3 plant - 1 insect, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ");
            put(55,"type: GOLD, kingdom: PLANT, points: 2(COVERED_ANGLE), condition:3 plant - 1 animal, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(56,"type: GOLD, kingdom: PLANT, points: 2(COVERED_ANGLE), condition:3 plant - 1 fungi, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ");
            put(57,"type: GOLD, kingdom: PLANT, points: 3, condition:3 plant, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:HIDDEN, bottom_left_angle:FEATHER ");
            put(58,"type: GOLD, kingdom: PLANT, points: 3, condition:3 plant, top_left_angle:PARCHMENT , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:HIDDEN ");
            put(59,"type: GOLD, kingdom: PLANT, points: 3, condition:3 plant, top_left_angle:HIDDEN , top_right_angle:INK_BOTTLE, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(60,"type: GOLD, kingdom: PLANT, points: 5, condition:5 plant top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:HIDDEN ");

            put(61,"type: GOLD, kingdom: ANIMAL, points: 1(INK_BOTTLE), condition:2 animal - 1 insect, top_left_angle:INK_BOTTLE , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(62,"type: GOLD, kingdom: ANIMAL, points: 1(PARCHMENT), condition:2 animal - 1 plant, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:PARCHMENT, bottom_left_angle:EMPTY ");
            put(63,"type: GOLD, kingdom: ANIMAL, points: 1(FEATHER), condition:2 animal - 1 fungi, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:FEATHER ");
            put(64,"type: GOLD, kingdom: ANIMAL, points: 2(COVERED_ANGLE), condition:3 animal - 1 insect, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(65,"type: GOLD, kingdom: ANIMAL, points: 2(COVERED_ANGLE), condition:3 animal - 1 fungi, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ");
            put(66,"type: GOLD, kingdom: ANIMAL, points: 2(COVERED_ANGLE), condition:3 animal - 1 plant, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ");
            put(67,"type: GOLD, kingdom: ANIMAL, points: 3, condition:3 animal, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:HIDDEN, bottom_left_angle:PARCHMENT ");
            put(68,"type: GOLD, kingdom: ANIMAL, points: 3, condition:3 animal, top_left_angle:EMPTY , top_right_angle:INK_BOTTLE, bottom_right_angle:HIDDEN, bottom_left_angle:HIDDEN ");
            put(69,"type: GOLD, kingdom: ANIMAL, points: 3, condition:3 animal, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:FEATHER, bottom_left_angle:HIDDEN ");
            put(70,"type: GOLD, kingdom: ANIMAL, points: 5, condition:5 animal, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");

            put(71,"type: GOLD, kingdom: INSECT, points: 1(FEATHER), condition:2 insect - 1 plant, top_left_angle:EMPTY , top_right_angle:FEATHER, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(72,"type: GOLD, kingdom: INSECT, points: 1(PARCHMENT), condition:2 insect - 1 animal, top_left_angle:EMPTY , top_right_angle:HIDDEN , bottom_right_angle:EMPTY, bottom_left_angle:PARCHMENT ");
            put(73,"type: GOLD, kingdom: INSECT, points: 1(INK_BOTTLE), condition:2 insect - 1 fungi, top_left_angle:HIDDEN , top_right_angle:EMPTY, bottom_right_angle:INK_BOTTLE, bottom_left_angle:EMPTY ");
            put(74,"type: GOLD, kingdom: INSECT, points: 2(COVERED_ANGLE), condition:3 insect - 1 animal, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:HIDDEN ");
            put(75,"type: GOLD, kingdom: INSECT, points: 2(COVERED_ANGLE), condition:3 insect - 1 plant, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(76,"type: GOLD, kingdom: INSECT, points: 2(COVERED_ANGLE), condition:3 insect - 1 fungi, top_left_angle:EMPTY , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ");
            put(77,"type: GOLD, kingdom: INSECT, points: 3, condition:3 insect, top_left_angle:INK_BOTTLE , top_right_angle:HIDDEN, bottom_right_angle:HIDDEN, bottom_left_angle:EMPTY ");
            put(78,"type: GOLD, kingdom: INSECT, points: 3, condition:3 insect, top_left_angle:EMPTY , top_right_angle:PARCHMENT, bottom_right_angle:HIDDEN, bottom_left_angle:HIDDEN ");
            put(79,"type: GOLD, kingdom: INSECT, points: 3, condition:3 insect, top_left_angle:HIDDEN , top_right_angle:HIDDEN, bottom_right_angle:EMPTY, bottom_left_angle:FEATHER ");
            put(80,"type: GOLD, kingdom: INSECT, points: 5, condition:5 insect, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:HIDDEN ");

            put(81, String.format("""
                            type: OBJECTIVE, points: 2,
                                  %s███%s
                               %s███%s
                            %s███%s
                            """,
                    AnsiColor.RED.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.RED.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.RED.getCode(), AnsiColor.RESET.getCode()));
            put(82, String.format("""
                            type: OBJECTIVE, points: 2,
                            %s███%s
                               %s███%s
                                  %s███%s
                            """,
                    AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode()));
            put(83, String.format("""
                            type: OBJECTIVE, points: 2,
                                  %s███%s
                               %s███%s
                            %s███%s
                            """,
                    AnsiColor.BLUE.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.BLUE.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.BLUE.getCode(), AnsiColor.RESET.getCode()));
            put(84, String.format("""
                            type: OBJECTIVE, points: 2,
                            %s███%s
                               %s███%s
                                  %s███%s
                            """,
                    AnsiColor.MAGENTA.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.MAGENTA.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.MAGENTA.getCode(), AnsiColor.RESET.getCode()));
            put(85, String.format("""
                            type: OBJECTIVE, points: 2,
                            %s███%s
                            %s███%s
                               %s███%s
                            """,
                    AnsiColor.RED.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.RED.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode()));
            put(86, String.format("""
                            type: OBJECTIVE, points:2,
                               %s███%s
                               %s███%s
                            %s███%s
                            """,
                    AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.MAGENTA.getCode(), AnsiColor.RESET.getCode()));
            put(87, String.format("""
                            type: OBJECTIVE, points:2,
                               %s███%s
                            %s███%s
                            %s███%s
                            """,
                    AnsiColor.RED.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.BLUE.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.BLUE.getCode(), AnsiColor.RESET.getCode()));
            put(88, String.format("""
                            type: OBJECTIVE, points:2,
                            %s███%s
                               %s███%s
                               %s███%s
                            """,
                    AnsiColor.BLUE.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.MAGENTA.getCode(), AnsiColor.RESET.getCode(),
                    AnsiColor.MAGENTA.getCode(), AnsiColor.RESET.getCode()));

            put(89,"type: OBJECTIVE, set of 3 FUNGI, points: 2");
            put(90,"type: OBJECTIVE, set of 3 PLANT, points: 2");
            put(91,"type: OBJECTIVE, set of 3 ANIMAL, points: 2");
            put(92,"type: OBJECTIVE, set of 3 INSECT, points: 2");
            put(93,"type: OBJECTIVE, set of 1_INK_BATTLE+1_FEATHER+1_PARCHMENT, points: 3");
            put(94,"type: OBJECTIVE, set of 2 PARCHMENT, points: 2");
            put(95,"type: OBJECTIVE, set of 2 INK_BOTTLE, points: 2");
            put(96,"type: OBJECTIVE, set of 2 FEATHER, points: 2");

            put( 97,"type: INITIAL, top_left_angle:FUNGI, top_right_angle:PLANT, bottom_right_angle:ANIMAL, bottom_left_angle:INSECT");
            put( 98,"type: INITIAL, top_left_angle:PLANT , top_right_angle:ANIMAL, bottom_right_angle:INSECT, bottom_left_angle:FUNGI");
            put( 99,"type: INITIAL, top_left_angle:INSECT , top_right_angle:ANIMAL, bottom_right_angle:PLANT, bottom_left_angle:FUNGI");
            put(100,"type: INITIAL, top_left_angle:PLANT , top_right_angle:INSECT, bottom_right_angle:Fungi, bottom_left_angle:ANIMAL");
            put(101,"type: INITIAL, top_left_angle:INSECT , top_right_angle:FUNGI , bottom_right_angle:ANIMAL, bottom_left_angle:PLANT");
            put(102,"type: INITIAL, top_left_angle:FUNGI , top_right_angle:ANIMAL, bottom_right_angle:INSECT, bottom_left_angle:PLANT");

            put(197,"type: INITIAL, center:INSECT ,top_left_angle:EMPTY, top_right_angle:PLANT, bottom_right_angle:EMPTY, bottom_left_angle:INSECT");
            put(198,"type: INITIAL, center:FUNGI , top_left_angle:ANIMAL , top_right_angle:EMPTY, bottom_right_angle:FUNGI, bottom_left_angle:EMPTY");
            put(199,"type: INITIAL, center:PLANT.FUNGI , top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY");
            put(200,"type: INITIAL, center:ANIMAL.INSECT , top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY");
            put(201,"type: INITIAL, center:ANIMAL.INSECT.PLANT , top_left_angle:EMPTY , top_right_angle:EMPTY , bottom_right_angle:HIDDEN, bottom_left_angle:HIDDEN");
            put(202,"type: INITIAL, center:PLANT.ANIMAL.FUNGI , top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:HIDDEN, bottom_left_angle:HIDDEN");

        }
    };

    public static String getCard(int id, Boolean isBackSide){
        if (isBackSide){
            if (( 0 < id && id < 11) ) { return "type: RESOURCE, kingdom: FUNGI, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ";}
            if ((10 < id && id < 21) ) { return "type: RESOURCE, kingdom: PLANT, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ";}
            if ((20 < id && id < 31) ) { return "type: RESOURCE, kingdom: ANIMAL, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ";}
            if ((30 < id && id < 41) ) { return "type: RESOURCE, kingdom: INSECT, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ";}

            if ((40 < id && id < 51) ) { return "type: GOLD, kingdom: FUNGI, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ";}
            if ((50 < id && id < 61) ) { return "type: GOLD, kingdom: PLANT, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ";}
            if ((60 < id && id < 71) ) { return "type: GOLD, kingdom: ANIMAL, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ";}
            if ((70 < id && id < 81) ) { return "type: GOLD, kingdom: INSECT, top_left_angle:EMPTY , top_right_angle:EMPTY, bottom_right_angle:EMPTY, bottom_left_angle:EMPTY ";}

            if ((96 < id && id < 103) ) { return card.get(id+100);}

        }
        return card.get(id);
    }

}
