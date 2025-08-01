export interface TypeDefinition {
    name: string;
    description: string;
}

export interface ValidationRule {
    type: 'min' | 'max' | 'notNull' | 'notBlank';
    parameters?: (string | number)[];
}

export interface FieldDefinition {
    name: string;
    type: TypeDefinition;
    option?: boolean;
    validations?: ValidationRule[];
}

export interface EntityDefinition {
    name: string;
    fields: FieldDefinition[];
}

export interface ServiceDefinition {
    name: string;
    entities: EntityDefinition[];
}