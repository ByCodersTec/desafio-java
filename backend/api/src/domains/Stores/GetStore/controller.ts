import { Request, Response } from 'express';
import Stores from '../../../entities/Stores';
import { IController } from '../../types';
import UseCase from './useCase';


export default class Controller implements IController {
  constructor(private useCase: UseCase)
  {}

  async handle(request: Request, response: Response) {
    try {
      const { id } = request.params;
      const data: Stores = await this.useCase.execute(id);
      
      return response.status(200).json(data);
    } catch (err) {
      console.log(err);
      return response.status(400).json({
        message: err.message || 'Error',
      });
    }
  }
}