export class IndexedDB {
    private dbName: string
    private dbVersion: number
    private db: IDBDatabase | null

    constructor(dbName: string, dbVersion: number) {
        this.dbName = dbName;
        this.dbVersion = dbVersion;
        this.db = null;
    }

    async openDB(storeName: string): Promise<void> {
        return new Promise((resolve, reject) => {
            const request = indexedDB.open(this.dbName, this.dbVersion);
            
            request.onupgradeneeded = (event : any) => {
                const db = (event.target as IDBOpenDBRequest).result;
                if (!db.objectStoreNames.contains(storeName)) {
                    db.createObjectStore(storeName, { keyPath: 'id', autoIncrement: true });
                }
            };

            request.onsuccess = (event : any) => {
                this.db = (event.target as IDBOpenDBRequest).result;
                resolve();
            };

            request.onerror = (event : any) => {
                reject((event.target as IDBOpenDBRequest).error);
            };
        });
    }

    async addData(storeName: string, data: any): Promise<void> {
        if (!this.db) {
            throw new Error('Database is not open');
        }
        return new Promise((resolve, reject) => {
            const transaction = this.db!.transaction(storeName, 'readwrite');
            const store = transaction.objectStore(storeName);
            const request = store.add(data);

            request.onsuccess = () => {
                resolve();
            };
            request.onerror = (event : any) => {
                reject((event.target as IDBRequest).error);
            };
        });
    }

    async getData(storeName: string, id: number): Promise<any> {
        if (!this.db) {
            throw new Error('Database is not open');
        }
        return new Promise((resolve, reject) => {
            const transaction = this.db!.transaction(storeName, 'readonly');
            const store = transaction.objectStore(storeName);
            const request = store.get(id);
            
            request.onsuccess = (event : any) => {
                resolve((event.target as IDBRequest).result);
            };
            request.onerror = (event : any) => {
                reject((event.target as IDBRequest).error);
            };  
        });
    }

    async getAllData(storeName: string): Promise<any[]> {
        if (!this.db) {
            throw new Error('Database is not open');
        }
        return new Promise((resolve, reject) => {
            const transaction = this.db!.transaction(storeName, 'readonly');
            const store = transaction.objectStore(storeName);
            const request = store.getAll();
            request.onsuccess = (event : any) => {
                resolve((event.target as IDBRequest).result);
            };
            request.onerror = (event : any) => {
                reject((event.target as IDBRequest).error);
            }
        });
    }

    async deleteData(storeName: string, id: number): Promise<void> {
        if (!this.db) {
            throw new Error('Database is not open');
        }
        return new Promise((resolve, reject) => {
            const transaction = this.db!.transaction(storeName, 'readwrite');
            const store = transaction.objectStore(storeName);
            const request = store.delete(id);
            request.onsuccess = () => {
                resolve();
            };
            request.onerror = (event : any) => {
                reject((event.target as IDBRequest).error);
            }
        });
    }
}