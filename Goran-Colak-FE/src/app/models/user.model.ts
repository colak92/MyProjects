import { Role } from './role.model';

export class User {
    id: number;
    username: string;
    email: string;
    password: string;
    confirmPassword: string;
    roles: Role[];
}