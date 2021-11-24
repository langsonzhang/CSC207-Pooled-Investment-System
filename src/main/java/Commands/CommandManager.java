package Commands;

import org.reflections.Reflections;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * This is a Class for centralized manipulation of Commands.
 * It provides Class templates as well as Class Objects themselves.
 * The use of reflection in this Class allows it to self-inspect for
 * all Command subclasses.
 *
 * Author Langson Zhang
 * Date: Nov 11 2021
 * Version: 1.0
 */
public class CommandManager {
    private static CommandManager instance; // The instance

    private Set<Class<? extends Command>> cmdClasses; // Class objects for the commands
    private List<Command> cmdTemplates; // Template instances of the commands

    //______________________________________________ Constructors ______________________________________________________

    /**
     * Default Constructor.
     * Scans for all subclasses of Command and creates the template list and Class list.
     */
    private CommandManager() {
        // We use the reflection api to scan for all subtypes of Command
        cmdClasses = new Reflections(Command.class).getSubTypesOf(Command.class);
        cmdTemplates = new ArrayList<Command>();

        for (Class c : cmdClasses){
            cmdTemplates.add(generate(c));
        }
    }

    static { // Static initializer
        instance = new CommandManager();
    }

    //_________________________________________________ Methods ________________________________________________________

    /**
     * Generates and returns an instance of a Command given the Class Object.
     * @param cmdClass is the Class Object associated with [T]
     * @param <T> is the Command subclass to be generated
     * @returns a Command of subclass [T], null if failed
     */
    public <T extends Command> T generate(Class<T> cmdClass){
        try{
            return cmdClass.getConstructor().newInstance();
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds the Command that is associated with the given String
     * @param cmdString is the String identifier/name of the Command
     * @returns the Class of the Command if matched successfully, null if not
     */
    public Class<? extends Command> find(String cmdString){
        for (Command cmd : this.cmdTemplates) {
            if (cmd.name().trim().toLowerCase() == cmdString.trim().toLowerCase()){
                return cmd.getClass();
            }
        }
        return null;
    }

    //_________________________________________________ Getters and Setters ____________________________________________

    /**
     * Gets the singleton instance
     * @returns the instance
     */
    public static CommandManager getInstance(){
        return instance;
    }

}
