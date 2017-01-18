/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */
package org.cloudbus.cloudsim.resources;

/**
 * An interface to represent a physical or virtual resource (like RAM, CPU
 * or Bandwidth) that doesn't provide direct features to change allocated
 * amount of resource. Objects that directly implement this interface
 * are supposed to define the capacity and amount of allocated resource
 * in their constructors.
 *
 * @author Uros Cibej
 * @author Anthony Sulistio
 * @author Manoel Campos da Silva Filho
 * @since CloudSim Plus 1.0
 */
public interface Resource extends ResourceCapacity {
    /**
     * Gets the amount of the resource that is available (free).
     *
     * @return the amount of available resource
     *
     * @pre $none
     * @post $none
     */
    long getAvailableResource();

    /**
     * Gets the current total amount of allocated resource.
     *
     * @return amount of allocated resource
     *
     * @pre $none
     * @post $none
     */
    long getAllocatedResource();

    /**
     * Checks if there is a specific amount of resource available (free).
     * @param amountToCheck the amount of resource to check if is free.
     * @return true if the specified amount is free; false otherwise
     */
    boolean isResourceAmountAvailable(long amountToCheck);

    /**
     * Checks if there the capacity required for the given resource is available (free)
     * at this resource. This method is commonly used to check if there is a specific
     * amount of resource free at a physical resource (this Resource instance)
     * that is required by a virtualized resource (the given Resource).
     *
     * @param resource the resource to check if its capacity is available at the current resource
     * @return true if the capacity required by the given Resource is free; false otherwise
     * @see #isResourceAmountAvailable(long)
     */
    default boolean isResourceAmountAvailable(Resource resource){
        return isResourceAmountAvailable(resource.getCapacity());
    }


    /**
     * Checks if there is a specific amount of resource available (free),
     * where such amount is a double value that will be converted to long.
     *
     * <p>This method is just a shorthand to avoid explicitly converting
     * a double to long.</p>
     *
     * @param amountToCheck the amount of resource to check if is free.
     * @return true if the specified amount is free; false otherwise
     * @see #isResourceAmountAvailable(long)
     */
    default boolean isResourceAmountAvailable(double amountToCheck){
        return isResourceAmountAvailable((long)amountToCheck);
    }

    /**
     * Checks if the resource is full or not.
     *
     * @return <tt>true</tt> if the storage is full, <tt>false</tt> otherwise
     */
    boolean isFull();

    /**
     * Gets the current percentage of resource utilization in scale from 0 to 1.
     * It is the percentage of the total resource capacity that is currently allocated.
     * @return current resource utilization (allocation) percentage in scale from 0 to 1
     */
    default double getUtilization() {
        return getCapacity() > 0 ? getAllocatedResource() / (double)getCapacity() : 0.0;
    }

    /**
     * An attribute that implements the Null Object Design Pattern for {@link Resource}
     * objects.
     */
    Resource NULL = new Resource() {
        @Override public long getAvailableResource() { return 0; }
        @Override public long getAllocatedResource() { return 0; }
        @Override public boolean isResourceAmountAvailable(long amountToCheck) { return false; }
        @Override public boolean isFull() { return false; }
        @Override public long getCapacity() { return 0; }
    };

}
