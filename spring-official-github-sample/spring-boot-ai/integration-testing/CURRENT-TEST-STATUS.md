# Current Integration Test Status

**Last Updated**: December 2024  
**Quick Reference**: This file tracks the exact status of all integration tests

## 📊 Overall Status

- **Total Examples**: 33
- **Tests Created**: 24 (73%)
- **Actually Working**: 18 (54%)
- **In CI**: 17 (52%)

## ✅ PASSING in CI (17 tests)

```
✓ models/chat/helloworld
✓ kotlin/kotlin-hello-world
✓ kotlin/kotlin-function-callback (added to CI)
✓ misc/spring-ai-java-function-callback
✓ agents/reflection
✓ agents/tools-and-agent-tools
✓ agentic-patterns/chain-workflow
✓ agentic-patterns/evaluator-optimizer-pattern
✓ agentic-patterns/orchestrator-workers
✓ agentic-patterns/parallelization
✓ agentic-patterns/routing-agent
✓ model-context-protocol/brave
✓ model-context-protocol/client-starter/starter-default-client
✓ model-context-protocol/filesystem
✓ model-context-protocol/sqlite/chatbot
✓ model-context-protocol/sqlite/simple
✓ model-context-protocol/web-search/brave-chatbot
✓ model-context-protocol/web-search/brave-starter
```

## ⚠️ FIXED but NOT in CI (1 test)

```
⚠️ prompt-engineering/prompt-engineering-patterns
   - Fixed: Timeout increased 120s → 300s
   - Status: Works locally
   - Action: Add to CI
```

## ❌ FAILING/INCOMPLETE (6 tests)

### Web Servers (2) - WEAK VALIDATION

```
❌ misc/openai-streaming-response
   - Problem: Only validates SSE format, not actual content
   - Fix needed: Parse SSE chunks, validate joke response
   
❌ model-context-protocol/weather/starter-webmvc-server
   - Problem: Only checks content-type, no MCP protocol test
   - Fix needed: Send MCP messages, validate tool listing
```

### Client-Server Pairs (2) - NEED ORCHESTRATION

```
❌ model-context-protocol/dynamic-tool-update/client
   - Problem: Needs server running
   
❌ model-context-protocol/dynamic-tool-update/server
   - Problem: Needs client to connect
```

### Infrastructure Issues (2)

```
❌ kotlin/rag-with-kotlin
   - Problem: Requires Docker for vector database
   
❌ model-context-protocol/client-starter/starter-webflux-client
   - Problem: Connection refused (needs MCP servers)
```

## 🔧 No Tests Created (9 examples)

```
- agents/creative-writing
- agents/github-assistant
- agents/naive-rag
- model-context-protocol/annotations/client
- model-context-protocol/annotations/server
- model-context-protocol/web-search/client
- model-context-protocol/weather/client
- model-context-protocol/weather/server
- prompt-engineering/prompt-templating
```

---
**Note**: This file should be updated whenever test status changes.