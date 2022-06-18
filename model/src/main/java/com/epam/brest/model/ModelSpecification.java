package com.epam.brest.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(name = "model-specification", description = "Specification of car model")
public class ModelSpecification {

    /**
     * @serialField modelId Integer.
     */

    @Schema(hidden = true)
    private Integer modelId;

    /**
     * @serialField modelName String.
     */

    @Schema(name = "modelName", description = "Car's model name", example = "NISSAN")
    private String modelName;

    /**
     * @serialField description String.
     */

    @Schema(name = "description", description = "Model's description",
            example = "Passenger car: made in Japan")
    private String description;

    /**
     * @serialField maxSpeed Integer.
     */

    @Schema(name = "max-speed", description = "Model's max speed", example = "200")
    private Integer maxSpeed;

    /**
     * @serialField carryingCapacity Integer.
     */

    @Schema(name = "carrying-capacity", description = "Model's max carrying capacity",
            example = "12000")
    private Integer carryingCapacity;

    /**
     * @Constructor without parameters.
     */

    public ModelSpecification() {
    }

    /**
     * @Constructor
     *
     * @param modelId Integer.
     * @param modelName String.
     * @param description String.
     * @param maxSpeed Integer.
     * @param carryingCapacity Integer.
     */

    public ModelSpecification(final Integer modelId, final String modelName,
                              final String description, final Integer maxSpeed,
                              final Integer carryingCapacity) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.description = description;
        this.maxSpeed = maxSpeed;
        this.carryingCapacity = carryingCapacity;
    }

    /**
     * @Constructor
     *
     * @param modelName String.
     * @param description String.
     * @param maxSpeed Integer.
     * @param carryingCapacity Integer.
     */

    public ModelSpecification(final String modelName, final String description,
                              final Integer maxSpeed, final Integer carryingCapacity) {
        this.modelName = modelName;
        this.description = description;
        this.maxSpeed = maxSpeed;
        this.carryingCapacity = carryingCapacity;
    }

    /**
     * Getter for modelId.
     *
     * @return modelId.
     */

    public Integer getModelId() {
        return modelId;
    }

    /**
     * Setter for modelId.
     *
     * @param  modelId Integer.
     */

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * Getter for modelName.
     *
     * @return modelName.
     */

    public String getModelName() {
        return modelName;
    }

    /**
     * Setter for modelName.
     *
     * @param  modelName String.
     */

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Getter for description.
     *
     * @return description.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Setter for description.
     *
     * @param  description String.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for maxSpeed.
     *
     * @return maxSpeed Integer.
     */

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Setter for maxSpeed.
     *
     * @param  maxSpeed Integer.
     */

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Getter for carryingCapacity.
     *
     * @return carryingCapacity Integer.
     */

    public Integer getCarryingCapacity() {
        return carryingCapacity;
    }

    /**
     * Setter for carryingCapacity.
     *
     * @param  carryingCapacity Integer.
     */

    public void setCarryingCapacity(Integer carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelSpecification that = (ModelSpecification) o;
        return Objects.equals(modelId, that.modelId) && Objects.equals(modelName, that.modelName) && Objects.equals(description, that.description) && Objects.equals(maxSpeed, that.maxSpeed) && Objects.equals(carryingCapacity, that.carryingCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, modelName, description, maxSpeed, carryingCapacity);
    }

    @Override
    public String toString() {
        return "ModelSpecification{"
                + "modelId=" + modelId
                + ", modelName='" + modelName + '\''
                + ", description='" + description + '\''
                + ", maxSpeed=" + maxSpeed
                + ", carryingCapacity=" + carryingCapacity
                + '}';
    }
}
