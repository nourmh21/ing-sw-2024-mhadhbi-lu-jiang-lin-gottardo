package it.polimi.ingsw.model.enums;

/**
 *     FFF: set of 3 FUNGI
 *     AAA: set of 3 animal
 *     PPP: set of 3 PLANT
 *     III: set of 3 INSECT
 *     BB: set of 2 INK_BOTTLE
 *     FF: set of 2 FEATHER
 *     PP: set of 2 PARCHMENT
 *     BFP: set of 1 ink bottle, 1  feather and 1 parchment
 *     REDG: position card 3 red increase
 *     BLUEG: position card 3 blue increase
 *     VIOLAD: position card 3 viola decrease
 *     GREEND: position card 3 green decrease
 *     BBR: position card 3 blue_blue red
 *     VVB: position card 3 viola_viola blue
 *     RRG: position card 3 red_red green
 *     GGV: position card 3 green_green viola
 */
public enum GoalType {
    //set of 3 FUNGI
    FFF,
    //set of 3 animal
    AAA,
    //set of 3 PLANT
    PPP,
    //set of 3 INSECT
    III,
    //set of 2 INK_BOTTLE
    BB,
    //set of 2 FEATHER
    FF,
    //set of 2 PARCHMENT
    PP,
    //set of 1 ink bottle, 1  feather and 1 parchment
    BFP,
    //position card 3 red increase
    REDG,
    //position card 3 blue increase
    BLUEG,
    //position card 3 viola decrease
    VIOLAD,
    //position card 3 green decrease
    GREEND,
    //position card 3 blue_blue red
    BBR,
    //position card 3 viola_viola blue
    VVB,
    //position card 3 red_red green
    RRG,
    //position card 3 green_green viola
    GGV
}
