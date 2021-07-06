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

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che posso
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author pierpaolo
 */
public class PasquettaDaComa extends GameDescription {

    @Override
    public void init() throws Exception {
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv", "i", "I"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
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
        push.setAlias(new String[]{"spingi", "attiva"});
        getCommands().add(push);
        //Rooms
        Room forest = new Room(0, "Mercadante", "E' pasquestta e stranamente non piove."
                + "\nSei nella famosa foresta di Mercadante con il tuo gruppo di amici "
                + "\ne dopo qualche birra di troppo iniziate a giocare con la palla."
                + "\nSenza motivo afferri la palla e la calci troppo lontano...");
        forest.setLook("Non c'è niente da osservare!"
                + "\nVai a prendere la palla prima che i tuoi amici inzino ad agitarsi");
        Room garden = new Room(1, "Giardino", "Recuperando la palla non hai visto dove mettevi i piedi"
                + "\nsei inciampato e hai battuto la testa e ti sei risvegliato all'estreno dell'unco bar presente nella foresta");
        garden.setLook("Proprio accanto a te noti delle giostrine per bambini mentre vicino l'ingresso, a nord, "
                + "\nc'è una veranda con dei tavoli apparecchiati. Ma... a terra c'è il tuo telefono!");
        Room mainRoom = new Room(2, "Stanza principale", "Entrando la porta alle tue spalle si chiude senza darti possibilità di uscita. "
                + "\nAll'interno sembra un classico bar, stranamente però manca il bancone e il frigo delle birre è vuoto");
        mainRoom.setLook("Noti una macchinetta del caffè proprio identica a quella del dipartimento di informatica e ci sono due porte una ad est e l'altra ad ovest.");
        Room warehouse = new Room(3, "Magazzino", "E' un magazzino, niente di più niente di meno...");
        warehouse.setLook("Ci sono dei tavolini e sedie di scorta, sotto uno di questi tavoli c'è nascasta una cassaforte."
                + "\nA sud scorgi una strana porta, quasi futuristica, diversa dalla porta ad est che riconduce alla stanza principale.");
        Room office = new Room(4, "Ufficio", "E' un ufficio arredato in stile 'Il Padrino', c'è odore di sigaro nell'aria...");
        office.setLook("Sono presenti diverse poltrone in pelle, una classica scrivania e sul muro è appesa una bacheca. "
                + "\nNon ci sono finestre, è presente solo una lampada da scrivania e l'unica porta presente è quella a nord "
                + "\nche ti riconduce al magazzino. (Chissà che affari loschi conducono qui)");
        Room bossRoom = new Room (5, "Area ristorante", "E' l'area che il bar utilizza come ristorante. Che fame...");
        bossRoom.setLook("Sono presenti tanti tavoli apparecchiati e arriva una forte luce dalla vetrata. C'è un uomo con la mascherina seduto ad un tavolo!");
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
        //obejcts
        AdvObject rides = new AdvObject(1, "giostrine", "Delle semplici giostrine per bambini.");
        rides.setAlias(new String[]{"giostre", "giostrine", "giostra", "giostrina"});
        garden.getObjects().add(rides);
        AdvObject table = new AdvObject(1, "tavolini", "Tavolini apparecchiati sulla veranda.");
        table.setAlias(new String[]{"tavolo", "tavoli", "tavolino", "tavolini", "veranda"});
        garden.getObjects().add(table);
        AdvObject telephone = new AdvObject(1, "telefono", "SCRIVERE MESSAGGIO DEL COMA ECC... MA MO NON MI INGOZZA XD");
        telephone.setAlias(new String[]{"telefono", "cellulare", "telefonino", "smartphone"});
        garden.getObjects().add(telephone);
        
        AdvObject fridge = new AdvObject(2, "frigo", "Il frigo delle birre. Peccato che è vuoto.");
        fridge.setAlias(new String[]{"frigo", "frigorifero"});
        mainRoom.getObjects().add(fridge);
        AdvObject distributor = new AdvObject(2, "macchinetta", "");
        distributor.setAlias(new String[]{"macchinetta", "distributore"});
        mainRoom.getObjects().add(fridge);
        
        AdvObject wTable = new AdvObject(3, "tavolini", "Tavolini e sedie di scorta.");
        wTable.setAlias(new String[]{"tavolo", "tavoli", "tavolino", "tavolini", "sedia", "sedie"});
        warehouse.getObjects().add(wTable);
        AdvObjectContainer safe = new AdvObjectContainer(3, "cassaforte", "Una strana e misteriosa cassaforte");
        safe.setAlias(new String[]{"cassaforte", "cassa", "forziere"});
        safe.setOpenable(true);
        safe.setPickupable(false);
        safe.setOpen(false);
        warehouse.getObjects().add(safe);
        
        AdvObject armchair = new AdvObject(4, "poltrona", "Poltrone in pelle. Molto comode!");
        armchair.setAlias(new String[]{"poltrona", "poltrone"});
        office.getObjects().add(armchair);
        AdvObject desk = new AdvObject(4, "scrivania", "Una bella scrivania. Sarà di una persona importante...");
        desk.setAlias(new String[]{"poltrona", "poltrone"});
        office.getObjects().add(desk);
        AdvObject lamp = new AdvObject(4, "lampada", "Una lampada al centro della scrivania. L'unica fonte di luce in questa stanza.");
        lamp.setAlias(new String[]{"lampada", "lampadina"});
        office.getObjects().add(lamp);
        //Inserire la bacheca
        
        AdvObject bTable = new AdvObject(5, "tavolini", "Tavoli apparecchiati. E' quasi pronto per mangiare.");
        bTable.setAlias(new String[]{"tavolo", "tavoli", "tavolino", "tavolini"});
        bossRoom.getObjects().add(bTable);
        //Inserire boss
        
        /*AdvObjectContainer wardrobe = new AdvObjectContainer(2, "armadio", "Un semplice armadio.");
        wardrobe.setAlias(new String[]{"guardaroba", "vestiario"});
        wardrobe.setOpenable(true);
        wardrobe.setPickupable(false);
        wardrobe.setOpen(false);
        yourRoom.getObjects().add(wardrobe);
        AdvObject toy = new AdvObject(3, "giocattolo", "Il gioco che ti ha regalato zia Lina.");
        toy.setAlias(new String[]{"gioco", "robot"});
        toy.setPushable(true);
        toy.setPush(false);
        wardrobe.add(toy);*/
        
        //set starting room
        setCurrentRoom(forest);
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (p.getCommand().getType() == CommandType.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    setCurrentRoom(getCurrentRoom().getSouth());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.INVENTORY) {
                out.println("Nel tuo inventario ci sono:");
                for (AdvObject o : getInventory()) {
                    out.println(o.getName() + ": " + o.getDescription());
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (getCurrentRoom().getLook() != null) {
                    out.println(getCurrentRoom().getLook());
                } else {
                    out.println("Non c'è niente di interessante qui.");
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        out.println("Hai raccolto: " + p.getObject().getDescription());
                    } else {
                        out.println("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    out.println("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Questa soluzione NON va bene poiché quando un oggetto contenitore viene richiuso è complicato
                * non rendere più disponibili gli oggetti contenuti rimuovendoli dalla stanza o dall'invetario.
                * Trovare altra soluzione.
                 */
                if (p.getObject() == null && p.getInvObject() == null) {
                    out.println("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                out.println("Hai aperto: " + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                            } else {
                                out.println("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof AdvObjectContainer) {
                                AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getInventory().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            out.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.PUSH) {
                //ricerca oggetti pushabili
                if (p.getObject() != null && p.getObject().isPushable()) {
                    out.println("Hai premuto: " + p.getObject().getName());
                    if (p.getObject().getId() == 3) {
                        end(out);
                    }
                } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                    out.println("Hai premuto: " + p.getInvObject().getName());
                    if (p.getInvObject().getId() == 3) {
                        end(out);
                    }
                } else {
                    out.println("Non ci sono oggetti che puoi premere qui.");
                }
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro! Non hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void end(PrintStream out) {
        out.println("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...tu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...è stata una morte CALOROSA...addio!");
        System.exit(0);
    }
}
