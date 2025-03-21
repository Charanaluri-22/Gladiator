import { Customer } from "./customer.model";
export interface Review {
    reviewId?: number;
    subject?: string;
    body?: string;
    rating?: number;
    dateCreated?: string;
    customer?: Customer;
}
