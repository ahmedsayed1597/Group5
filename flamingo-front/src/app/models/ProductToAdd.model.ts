import { FileHandle } from "./file-handle-model";

export interface ProductToAdd {
  productName: string;
  description: string;
  price: number;
  quantity: number;
  categoryId: number;
  image:string;
}
