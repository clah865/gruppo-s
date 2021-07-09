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
        Command read = new Command(CommandType.READ, "leggi");
        getCommands().add(read); 
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
        Room bossRoom = new Room(5, "Area ristorante", "E' l'area che il bar utilizza come ristorante. Che fame...");
        bossRoom.setLook("Sono presenti tanti tavoli apparecchiati e arriva una forte luce dalla vetrata. C'è un uomo con la mascherina seduto ad un tavolo!");
        //maps
        forest.setNorth(garden); //da togliere perchè deve inseguire la palla e si ritova nel giardino
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
        AdvObject telephone = new AdvObject(1, "telefono", "SCRIVERE MESSAGGIO DEL COMA ECC... MA MO NON MI INGOZZA XD");
        telephone.setAlias(new String[]{"telefono", "cellulare", "telefonino", "smartphone"});
        garden.getObjects().add(telephone);

        //stanza 2
        AdvObject distributor = new AdvObject(2, "macchinetta", "1) Caffè\n 2) Ginseng\n 3) Mocaccino\n 4) Caffè macchiato\n 5) Caffè al cioccolato\n");
        distributor.setAlias(new String[]{"macchinetta", "distributore"});
        mainRoom.getObjects().add(distributor);
        
        AdvObject cafe = new AdvObject(2, "caffe", "Un classico caffè");
        distributor.setAlias(new String[]{"caffè", "caffé", "caffe", "1"});
        mainRoom.getObjects().add(cafe);
        
        AdvObject ginseng = new AdvObject(2, "ginseng", "Un classico ginseng");
        distributor.setAlias(new String[]{"ginseng", "2"});
        mainRoom.getObjects().add(ginseng);
        
        AdvObject mocaccino = new AdvObject(2, "mocaccino", "Un classico mocaccino");
        distributor.setAlias(new String[]{"mocaccino", "3"});
        mainRoom.getObjects().add(mocaccino);
        
        AdvObject mCafe = new AdvObject(2, "caffe macchiato", "Un classico caffè macchiato");
        distributor.setAlias(new String[]{"caffè macchiato", "caffé macchiato", "caffe macchiato", "4"});
        mainRoom.getObjects().add(mCafe);
        
        AdvObject cCafe = new AdvObject(2, "caffe al cioccolato", "Un classico caffè al cioccolato");
        distributor.setAlias(new String[]{"caffè al cioccolato", "caffé al cioccolato", "caffe al cioccolato", "5"});
        mainRoom.getObjects().add(cCafe);

        //stanza 3
        AdvObjectContainer safe = new AdvObjectContainer(3, "cassaforte", "Una strana e misteriosa cassaforte");
        safe.setAlias(new String[]{"cassaforte", "cassa", "forziere"});
        safe.setPass("12345");
        safe.setPickupable(false);
        safe.setOpen(false);
        warehouse.getObjects().add(safe);
        
        AdvObject door = new AdvObject(3, "porta", "Una voce simile a quella di Carlo Conti chiede:\nDove si trova Foggia?"
                + "\nA) Puglia\nB)Iran\nC) Sardegna\n D) Turkmenistan");
        distributor.setAlias(new String[]{"porta", "portone"});
        door.setPass("A");
        warehouse.getObjects().add(door);
        
        //stanza 4
        AdvObject note = new AdvObject (4,"nota", "La password e' --> 12345");
        note.setAlias(new String[] {"foglio", "foglietto", "memo", "promemoria", "appunto"});
        office.getObjects().add(note);
        
        /*AdvObjectContainer board = new AdvObjectContainer(4, "bacheca", "Una bacheca che contiene un foglietto");
        safe.setOpenable(true);
        safe.setPickupable(false);
        safe.setOpen(false);
        office.getObjects().add(board);*/
       
        //Inserire boss

        
        //set starting room
        setCurrentRoom(forest);
    }

    @Override
    public String nextMove(ParserOutput p) {
        StringBuilder out = new StringBuilder();
        if (p.getCommand() == null) {

            out.append("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            boolean locked = false;
            boolean novisible = false;
            if (!getCurrentRoom().isLocked()) {
                if (getCurrentRoom().isVisible()) {
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
                    }
                } else {
                    novisible = true;
                }
            } else {
                locked = true;
            }
            
            if (p.getCommand().getType() == CommandType.INVENTORY) {
                out.append("Nel tuo inventario ci sono:");
                for (AdvObject o : getInventory()) {
                    out.append(o.getName() + ": " + o.getDescription());
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (getCurrentRoom().getLook() != null) {
                    out.append(getCurrentRoom().getLook());
                } else {
                    out.append("Non c'è niente di interessante qui.");
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        out.append("Hai raccolto: " + p.getObject().getDescription());
                    } else {
                        out.append("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    out.append("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Questa soluzione NON va bene poiché quando un oggetto contenitore viene richiuso è complicato
                * non rendere più disponibili gli oggetti contenuti rimuovendoli dalla stanza o dall'invetario.
                * Trovare altra soluzione.
                 */
                if (p.getObject() == null && p.getInvObject() == null) {
                    out.append("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                out.append("Hai aperto: " + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    out.append(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        out.append(" " + next.getName());
                                        it.remove();
                                    }
                                    out.append("");
                                }
                            } else {
                                out.append("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            out.append("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof AdvObjectContainer) {
                                AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    out.append(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getInventory().add(next);
                                        out.append(" " + next.getName());
                                        it.remove();
                                    }
                                    out.append("");
                                }
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            out.append("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            out.append("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.PUSH) {
                //ricerca oggetti pushabili
                if (p.getObject() != null && p.getObject().isPushable()) {
                    out.append("Hai premuto: " + p.getObject().getName());
                    if (p.getObject().getId() == 3) {
                        //end(out);
                    }
                } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                    out.append("Hai premuto: " + p.getInvObject().getName());
                    if (p.getInvObject().getId() == 3) {
                        //end(out);
                    }
                } else {
                    out.append("Non ci sono oggetti che puoi premere qui.");
                }
            }
            if (noroom) {
                out.append("Da quella parte non si può andare c'è un muro! Non hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (move) {
                out.append(getCurrentRoom().getDescription());
            } else if (novisible) {
                out.append("Sembra che non sia più possibile accedere alla zona");
            } else if (locked) {
            out.append("La porta è bloccata. Sulla porta compare un messaggio: 'bussare'.");
        }

        }
        return out.toString();
    }

    private void end(PrintStream out) {
        out.println("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...tu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...è stata una morte CALOROSA...addio!");
        System.exit(0);
    }
}
