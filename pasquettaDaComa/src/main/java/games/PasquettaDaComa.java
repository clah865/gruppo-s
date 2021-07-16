/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games;

import adventure.GameDescription;
import parser.ParserOutput;
import type.AdvObject;
import type.AdvObjectContainer;
import type.Command;
import type.CommandType;
import type.Room;
import java.io.PrintStream;
import java.util.Iterator;

public class PasquettaDaComa extends GameDescription {

    private final String WAREHOUSE_NAME = "Magazzino";
    private final String GARDEN_NAME = "Giardino";
    private final String KEY = "chiave";
    private final String SAFE = "cassaforte";
    private final String TELEPHONE = "telefono";
    private final String DOOR = "porta";

    @Override
    public void init() throws Exception {
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"Nord", "NORD"});
        getCommands().add(nord);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"Ovest", "OVEST"});
        getCommands().add(ovest);
        Command inventory = new Command(CommandType.INVENTORY, "inventario");
        inventory.setAlias(new String[]{"inv"});
        getCommands().add(inventory);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"bussa", "bussare"});
        getCommands().add(push);
        Command read = new Command(CommandType.READ, "leggi");
        read.setAlias(new String[]{"inserisci", "inserire", "usa", "utilizza"});
        getCommands().add(read);
        Command insertKey = new Command(CommandType.INSERT_KEY, "inserisci");
        insertKey.setAlias(new String[]{"usa", "utilizza"});
        getCommands().add(insertKey);
        Command knock = new Command(CommandType.KNOCK, "bussa");
        knock.setAlias(new String[]{"bussare"});
        getCommands().add(knock);
        Command drink = new Command(CommandType.DRINK, "bevi");
        getCommands().add(drink);

        //Rooms
        Room forest = new Room(0, "Mercadante", "E' pasquetta e stranamente non piove."
                + "\nSei nella famosa foresta di Mercadante con il tuo gruppo di amici e dopo qualche birra di troppo "
                + "\niniziate a giocare con la palla."
                + "\nSenza motivo afferri la palla e la calci troppo lontano...");
        forest.setLook("Non c'è niente da osservare!"
                + "\nVai a prendere la palla verso nord prima che i tuoi amici inzino ad agitarsi");
        Room garden = new Room(1, "Giardino", "Recuperando la palla non hai visto dove mettevi i piedi sei inciampato e hai battuto la testa"
                + "\ne ti sei risvegliato all'estreno dell'unico bar presente nella foresta. Osserverei in giro...");
        garden.setLook("Proprio accanto a te noti delle giostrine per bambini mentre vicino l'ingresso, a nord, "
                + "\nc'è una veranda con dei tavoli apparecchiati. Ma... a terra c'è il tuo telefono!");
        Room mainRoom = new Room(2, "Stanza principale", "All'interno sembra un classico bar, stranamente manca il bancone e il frigo delle birre è vuoto");
        mainRoom.setLook("Noti una macchinetta del caffè proprio identica a quella del dipartimento di informatica e ci sono"
                + "\ndue porte: una ad est e l'altra ad ovest.");
        Room warehouse = new Room(3, "Magazzino", "E' un magazzino, niente di più niente di meno...");
        warehouse.setLook("Ci sono dei tavolini e sedie di scorta, sotto uno di questi tavoli c'è nascasta una cassaforte."
                + "\nA sud c'è una strana porta, diversa dalla porta ad est che riconduce alla stanza principale.");
        Room office = new Room(4, "Ufficio", "E' un ufficio arredato in stile 'Il Padrino', c'è odore di sigaro nell'aria...");
        office.setLook("Sono presenti poltrone in pelle, una classica scrivania e sul muro è appesa una bacheca. "
                + "\nNon ci sono finestre, è presente solo una lampada da scrivania e l'unica porta presente è quella"
                + "\na nord che ti riconduce al magazzino. (Chissà che affari loschi conducono qui)");
        Room bossRoom = new Room(5, "Area ristorante", "E' l'area che il bar utilizza come ristorante. Che fame...");
        bossRoom.setLook("Sono presenti tanti tavoli apparecchiati e arriva una forte luce dalla vetrata. "
                + "\nC'è un uomo con la mascherina seduto ad un tavolo!");
        //maps
        forest.setNorth(garden);
        garden.setNorth(mainRoom);
        mainRoom.setSouth(garden);
        mainRoom.setEast(warehouse);
        mainRoom.setWest(bossRoom);
        warehouse.setWest(mainRoom);
        warehouse.setSouth(office);
        office.setNorth(warehouse);
        bossRoom.setEast(mainRoom);
        getRooms().add(forest);
        getRooms().add(mainRoom);
        getRooms().add(warehouse);
        getRooms().add(office);
        getRooms().add(bossRoom);
        office.setLocked(true);
        //obejcts
        //stanza 1 
        AdvObject telephone = new AdvObject(1, "telefono", "C'è un messaggio:"
                + "\nSei in coma, l'unico modo per svegliarti è entrare nel bar a nord e prendere "
                + "\ndalla macchinetta un cappuccino alla genovese");
        telephone.setAlias(new String[]{"telefono", "cellulare", "telefonino", "smartphone"});
        garden.getObjects().add(telephone);

        //stanza 2
        AdvObjectContainer distributor = new AdvObjectContainer(2, "macchinetta", "1) Caffè\n 2) Ginseng\n 3) Mocaccino\n 4) Caffè macchiato\n 5) Caffè al cioccolato\n");
        distributor.setAlias(new String[]{"macchinetta", "distributore"});
        distributor.setPass(true);
        mainRoom.getObjects().add(distributor);

        AdvObject cafe = new AdvObject(2, "caffe", "Un classico caffè");
        cafe.setAlias(new String[]{"caffè", "caffé", "1"});
        distributor.add(cafe);

        AdvObject ginseng = new AdvObject(2, "ginseng", "Un classico ginseng");
        ginseng.setAlias(new String[]{"2"});
        distributor.add(ginseng);

        AdvObject mocaccino = new AdvObject(2, "mocaccino", "Un classico mocaccino");
        mocaccino.setAlias(new String[]{"mocaccino", "3"});
        distributor.add(mocaccino);

        AdvObject mCafe = new AdvObject(2, "caffe macchiato", "Un classico caffè macchiato");
        mCafe.setAlias(new String[]{"caffè macchiato", "caffé macchiato", "caffe macchiato", "4"});
        distributor.add(mCafe);

        AdvObject cCafe = new AdvObject(2, "caffe al cioccolato", "Un classico caffè al cioccolato");
        cCafe.setAlias(new String[]{"caffè al cioccolato", "caffé al cioccolato", "caffe al cioccolato", "5"});
        distributor.add(cCafe);

        //stanza 3
        AdvObjectContainer safe = new AdvObjectContainer(3, "cassaforte", "Una strana e misteriosa cassaforte");
        safe.setAlias(new String[]{"cassaforte", "cassa", "forziere"});
        safe.setOpenable(true);
        safe.setPass(true);
        safe.setPickupable(false);
        warehouse.getObjects().add(safe);

        AdvObject door = new AdvObject(3, "porta", "Una strana porta");
        door.setAlias(new String[]{"portone"});
        door.setPass(true);
        warehouse.getObjects().add(door);

        AdvObject sKey = new AdvObject(3, "codice segreto", "Il codice segreto per un caffè gratis è: 110721");
        sKey.setAlias(new String[]{"chiave", "codice", "chiave segreta"});
        safe.add(sKey);

        //stanza 4
        AdvObject note = new AdvObject(4, "chiave", "La chiave per aprire la cassaforte.");
        garden.getObjects().add(note);

        //Inserire boss
        //set starting room
        setCurrentRoom(forest);
    }

    @Override
    public String nextMove(ParserOutput p) {
        StringBuilder out = new StringBuilder();

        //move
        Room nextRoom;

        switch (p.getCommand().getType()) {
            case NORD:
                nextRoom = getCurrentRoom().getNorth();
                out = checkMove(nextRoom, out);
                break;
            case SOUTH:
                nextRoom = getCurrentRoom().getSouth();
                out = checkMove(nextRoom, out);
                break;
            case EAST:
                nextRoom = getCurrentRoom().getEast();
                out = checkMove(nextRoom, out);
                break;
            case WEST:
                nextRoom = getCurrentRoom().getWest();
                out = checkMove(nextRoom, out);
                break;
            case INSERT_KEY:
                out = insertKey(p, out);
                break;
            case INVENTORY:
                out.append("Nel tuo inventario ci sono:\n");
                for (AdvObject o : getInventory()) {
                    out.append(o.getName() + ": " + o.getDescription() + "\n");
                }
                break;
            case LOOK_AT:
                if (getCurrentRoom().getLook() != null) {
                    out.append(getCurrentRoom().getLook());
                } else {
                    out.append("Non c'è niente di interessante qui.");
                }
                break;
            case PICK_UP:
                out = pickUp(p, out);
                break;
            case OPEN:
                out = open(p, out);
                break;
            case PUSH:
                out = knock(p, out);
                break;
            case READ:
                out = insertKey(p, out);
                break;
            default:
                out.append("Gli sviluppatori non avevano tempo di implementare questo comando!");
                break;

        }

        return out.toString();
    }

    private StringBuilder checkMove(Room nextRoom, StringBuilder out) {
        boolean flag = false;
        if (nextRoom != null) {
            if (nextRoom.isVisible()) {
                if (!nextRoom.isLocked()) {
                    if (getCurrentRoom().getName() == GARDEN_NAME) {
                        for (AdvObject o : getInventory()) {
                            if (o.getName() == TELEPHONE) {
                                flag = true;
                            }
                        }
                        if (flag == true) {
                            getCurrentRoom().setVisible(false);
                            setCurrentRoom(nextRoom);
                            out.append("Entrando la porta alle tue spalle si chiude senza darti possibilità di uscita.\n");
                            out.append(getCurrentRoom().getDescription());
                        } else {
                            out.append("Non puoi proseguire da questa parte. Devi prima raccogliere qualcosa!");
                        }

                    } else {
                        setCurrentRoom(nextRoom);
                        out.append(getCurrentRoom().getDescription());
                    }

                } else {
                    out.append("La porta è bloccata. Sulla porta compare un messaggio: 'bussare'.");
                }
            } else {
                out.append("Sembra che non sia più possibile accedere alla zona");
            }
        } else {
            out.append("Da quella parte non si può andare! Gli sviluppatori non vogliono...");
        }
        return out;
    }

    private StringBuilder insertKey(ParserOutput p, StringBuilder out) {

        boolean unlocked = false;

        if (getCurrentRoom().getName() == WAREHOUSE_NAME) {
            for (AdvObject o : getInventory()) {
                if (o.getName() == KEY) {
                    for (AdvObject c : getCurrentRoom().getObjects()) {
                        if (c.getName() == SAFE) {
                            c.setPass(false);
                            unlocked = true;
                            getInventory().remove(o);
                            out.append("Hai sbloccato la cassaforte!");
                            break;
                        }
                    }
                }
            }
        } else {
            out.append("Luogo sbagliato!");
        }

        if (unlocked == false) {
            out.append("Non puoi aprire la cassaforte! Ti manca qualcosa.");
        }

        return out;
    }

    private StringBuilder pickUp(ParserOutput p, StringBuilder out) {

        try {
            Iterator<AdvObject> it = getCurrentRoom().getObjects().iterator();  //iteratore per scansionare gli oggetti nella stanza
            while (it.hasNext()) {
                AdvObject next = it.next();  //oggetto singolo all'interno della stanza
                if (next.isOpenable() && next instanceof AdvObjectContainer) {

                    AdvObjectContainer container = (AdvObjectContainer) next;

                    Iterator<AdvObject> nextIt = container.getList().iterator();  //iteratore per scansionere gli oggetti nel contenitore
                    while (nextIt.hasNext()) {
                        AdvObject nextContainer;
                        nextContainer = nextIt.next();  //oggetto singolo all'interno del contenitore
                        if (p.getObject().equals(nextContainer)) {
                            nextIt.remove();
                        }
                    }
                }
                if (p.getObject().equals(next)) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        out.append("Hai raccolto: " + p.getObject().getName() + "\n");
                        out.append(p.getObject().getDescription());
                        break;
                    } else {
                        out.append("Non puoi raccogliere questo oggetto.");
                    }
                }
            }
            return out;

        } catch (NullPointerException e) {
            out.append("Non c'è niente da raccogliere qui.");
            return out;
        }
        //return out;
    }

    private StringBuilder open(ParserOutput p, StringBuilder out) {

        if (p.getObject() == null && p.getInvObject() == null) {
            out.append("Non c'è niente da aprire qui.");
        } else {
            if (p.getObject() != null) {
                if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                    if (!p.getObject().isPass()) {
                        if (p.getObject() instanceof AdvObjectContainer) {
                            out.append("Hai aperto: " + p.getObject().getName());
                            AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                            p.getObject().setOpen(true);
                            if (!c.getList().isEmpty()) {
                                out.append(". Contiene:");
                                Iterator<AdvObject> it = c.getList().iterator();
                                while (it.hasNext()) {
                                    AdvObject next = it.next();
                                    out.append(" " + next.getName());
                                    getCurrentRoom().getObjects().add(next);
                                }
                                out.append("");
                            }
                        }
                    } else {
                        out.append("Non puoi aprire questo oggetto. Hai bisogno di una chiave!");
                    }

                } else {
                    AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                    out.append("Hai già aperto: " + p.getObject().getName());
                    if (!c.getList().isEmpty()) {
                        out.append(". Contiene:");
                        Iterator<AdvObject> it = c.getList().iterator();
                        while (it.hasNext()) {
                            AdvObject next = it.next();
                            out.append(" " + next.getName());
                        }
                        out.append("");
                    } else {
                        out.append(". Non ci sono oggetti all'interno!");
                    }

                }
            } else {
                out.append("Non puoi aprire questo oggetto.");
            }

        }
        return out;
    }

    private StringBuilder knock(ParserOutput p, StringBuilder out) {
        Room nextRoom;

        if (getCurrentRoom().getName() == WAREHOUSE_NAME) {
            if (p.getObject().getName() == DOOR) {
                p.getObject().setPass(false);
                nextRoom = getCurrentRoom().getSouth();
                nextRoom.setLocked(false);
                out.append("Hai sbloccato la porta!");
            }
        }
        return out;
    }

    private void end(PrintStream out) {
        out.println("Purtoppo non ce l'hai fatta... Non sei riuscito a svegliarti dal coma!");
        System.exit(0);
    }
}
