package brickset;

import repository.Repository;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }


    /**
     * return boolean if there is at least one heart as a name in LegoSet names
     * @return boolean
     */
    public boolean isThereAtLeaastOneWordInLegoSetNames ()
    {
       return getAll().stream()
                .anyMatch(LegoSet -> LegoSet.getName().toLowerCase().contains("heart"));
    }


    /**
     * prints out the Tags distinct and sorted without the null Tags
     */
    public void distinctTags()
    {
        getAll().stream()
                .filter(LegoSet -> LegoSet.getTags()!=null)
                .flatMap(LegoSet -> LegoSet.getTags().stream())
                .sorted()
                .distinct()
                .forEach(System.out::println);
    }


    /**
     * sum of pieces
     * @return integer
     */
    public int sumOfPieces()
    {
        return getAll().stream()
                .map(LegoSet::getPieces)
                .reduce(0, Integer::sum);


    }


    /**
     *
     * @return  Map
     */
    public Map<String, LegoSet> mapLegoSetToNumber(){
        return getAll().
                stream().
                collect(toMap(LegoSet::getNumber, Function.identity()));
    }


    /**
     *
     * @return Map
     */
    public Map<String, LegoSet> mapNamePieces()
    {
        return getAll().stream()
                .collect(groupingBy(LegoSet::getName, collectingAndThen(maxBy(Comparator.comparing(LegoSet::getPieces)), Optional::get)));

    }








    public static void main(String[] args)
    {

        var repository = new LegoSetRepository();

//        System.out.println( repository.ayhaga());

//        repository.flatmapexample();

//        System.out.println(repository.reduceEx());

//        System.out.println(repository.mapNumberName());

//        System.out.println(repository.mapNamePieces());







    }









}
