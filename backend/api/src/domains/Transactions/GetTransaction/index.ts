import Repository from "../../../infra/repository/PostgresRepository";
import Controller from "./controller";
import UseCase from "./useCase";
import Entity from "../../../entities/Transactions";

const repository = new Repository(Entity);
const useCase = new UseCase(repository);
const getController = new Controller(useCase);

export { getController };