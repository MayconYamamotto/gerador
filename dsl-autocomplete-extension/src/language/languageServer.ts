import * as vscode from 'vscode';
import { LanguageClient, LanguageClientOptions, ServerOptions, TransportKind } from 'vscode-languageclient/node';

export class LanguageServer {
    private client: LanguageClient;

    constructor() {
        const serverModule = require.resolve('./server');
        const debugOptions = { execArgv: ['--nolazy', '--inspect=6009'] };

        const serverOptions: ServerOptions = {
            run: { module: serverModule, transport: TransportKind.ipc },
            debug: { module: serverModule, transport: TransportKind.ipc, options: debugOptions }
        };

        const clientOptions: LanguageClientOptions = {
            documentSelector: [{ scheme: 'file', language: 'dsl' }],
            synchronize: {
                fileEvents: vscode.workspace.createFileSystemWatcher('**/.clientrc')
            }
        };

        this.client = new LanguageClient('dslLanguageServer', 'DSL Language Server', serverOptions, clientOptions);
    }

    public start() {
        this.client.start();
    }

    public stop() {
        this.client.stop();
    }
}